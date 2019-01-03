<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Toko;
use Validator;
use Illuminate\Support\Facades\Auth; 

class TokoController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $toko = Toko::orderBy('created_at','asc')->get();
        return response()->json(['data'=>$toko],200);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        
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
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        $input = $request->all();
        $input['create_id']=Auth::user()->id;
        $toko = Toko::create($input);
        
        $success =$toko;

        return response()->json(['data'=>$success],200);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'id'=> 'required'
        ]);
        if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        $data = Toko::where('id',$request->id)->first();
        return response()->json(['data'=>$data],200);
    }

    public function showByUser(Request $request){
        $data = Toko::where('pemilik_id',Auth::user()->id)->first();
        return response()->json(['data'=>$data],200);
    }

    public function detail(){
        $validator = Validator::make($request->all(), [ 
            'id'=> 'required'
        ]);
        if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        $data = Barang::where('id',$request->id)->first();
        return response()->json(['data'=>$data],200);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
