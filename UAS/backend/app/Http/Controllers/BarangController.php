<?php

namespace App\Http\Controllers;
use Validator;
use Auth;
use Illuminate\Http\Request;
use App\Barang;
use App\Toko;
class BarangController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $data=Barang::where('toko_id',$request->toko_id)->get();
        $nama_toko=$request->nama_toko;
        $nama_pemilik=$request->nama_pemilik;
        return view('barang.index',compact('data','nama_toko','nama_pemilik'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $toko = Toko::select('tokos.id','tokos.nama','users.nama as nama_pemilik')->leftjoin('users','tokos.pemilik_id','users.id')->get();
        return view('barang.create',compact('toko'));
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
            'harga' => 'required|integer',
            'deskripsi'=>'required',
            'gambar'=> 'required',
            'toko_id'=>'required|integer',
        ]);
        if ($validator->fails()) { 
            return redirect()->back()->with(['error'=>$validator->errors()]);         
        }

        $input = $request->all();
        $input['user_id']=Auth::user()->id;
        $barang = Barang::create($input);

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
        $data=Barang::where('id',$request->barang_id)->first();
        $toko = Toko::select('tokos.id','tokos.nama','users.nama as nama_pemilik')->leftjoin('users','tokos.pemilik_id','users.id')->get();
        return view('barang.edit',compact('data','toko'));
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
            'harga' => 'required|integer',
            'deskripsi'=>'required',
            'toko_id'=>'required|integer',
            'gambar'=> 'required',
            'id'=>'required'
        ]);
        if ($validator->fails()) { 
            return redirect()->back()->with(['error'=>$validator->errors()]);         
        }

        Barang::where('id',$request->id)->update([
            'nama'=>$request->nama,
            'harga'=>$request->harga,
            'deskripsi'=>$request->deskripsi,
            'gambar'=>$request->gambar,
            'toko_id'=>$request->toko_id
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
        Barang::where('id',$request->barang_id)->delete();
        return redirect()->back();
    }
}
