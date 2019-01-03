<?php

namespace App\Http\Controllers;
use Validator;
use Auth;
use Illuminate\Http\Request;
use App\Toko;
use App\User;
class TokoController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $data = Toko::
        select( 
            'tokos.id',
            'tokos.nama as nama_toko',
            'tokos.alamat as alamat_toko',
            'tokos.cover as gambar',
            'tokos.pemilik_id',
            'tokos.created_at',
            'tokos.updated_at',
            'users.nama as nama_pemilik',
            'users.email',
            'users.no_telp',
            'users.alamat as alamat_pemilik',
            'users.email')
        ->leftjoin('users','users.id','tokos.pemilik_id')
        ->groupBy(
            'tokos.id',
            'tokos.nama',
            'tokos.cover',
            'tokos.alamat',
            'tokos.pemilik_id',
            'tokos.created_at',
            'tokos.updated_at',
            'users.nama',
            'users.email',
            'users.no_telp',
            'users.alamat',
            'users.email'
        )
        ->orderBy('tokos.created_at','desc')
        ->get();
        return view('toko.index',compact('data'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $user = User::select('id','nama')->whereIn('level',[2,9])->get();
        return view('toko.create',compact('user'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'nama' => 'required', 
            'alamat' => 'required',
            'pemilik_id'=>'required|integer',
            'cover'=> 'required',
        ]);
        if ($validator->fails()) { 
            return redirect()->back()->with(['error'=>$validator->errors()]);         
        }
        $input = $request->all();
        $input['create_id']=Auth::user()->id;
        $toko = Toko::create($input);
        
        $success =$toko;

        return redirect(route('toko.index'));
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit(Request $request)
    {
        $data=Toko::where('id',$request->toko_id)->first();
        $user = User::select('id','nama')->whereIn('level',[2,9])->get();
        return view('toko.edit',compact('data','user'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'nama' => 'required', 
            'alamat' => 'required',
            'pemilik_id'=>'required|integer',
            'cover'=> 'required',
        ]);
        if ($validator->fails()) { 
            return redirect()->back()->with(['error'=>$validator->errors()]);         
        }

        Toko::where('id',$request->id)->update([
            'nama'=>$request->nama,
            'alamat'=>$request->alamat,
            'cover'=>$request->cover,
            'pemilik_id'=>$request->pemilik_id
        ]);

        return redirect(route('toko.index'));
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request)
    {
        Toko::where('id',$request->toko_id)->delete();
        return redirect()->back();
    }
}
