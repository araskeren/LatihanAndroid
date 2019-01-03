<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Barang;
use App\User;
use App\Transaksi;
use App\Toko;
use Validator;
use Illuminate\Support\Facades\Auth; 

class TransaksiController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $data = Transaksi::orderBy('created_at','asc')->get();
        return response()->json(['data'=>$data],200);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
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
            'barang_id' => 'required', 
        ]);
        if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        $user_id=Auth::user()->id;
        $cek=Transaksi::where('user_id',$user_id)
        ->where('barang_id',$request->barang_id)->whereNull('status')->first();
        if(count($cek)>0){
            return response()->json(['data'=>$cek],200);
        }
        $input = $request->all();
        $input['user_id']=Auth::user()->id;
        $data = Transaksi::create($input);
        return response()->json(['data'=>$data],200);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show()
    {
        $data = Transaksi::where('user_id',Auth::user()->id)->whereNull('status')->get();
        $total=0;
        foreach($data as $i){
            $barang = Barang::where('id',$i->barang_id)->first();
            $toko = Toko::select('nama')->where('id',$barang->toko_id)->first();
            $barang->toko=$toko->nama;
            $i->barang=$barang;
            $total+=$barang->harga;
        }

        return response()->json(['data'=>$data,'total'=>$total],200);
    }

    public function showByToko(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'toko_id' => 'required', 
            'status' => 'required'
        ]);
        if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        $data = Transaksi::
        select( 
            'transaksis.id',
            'barangs.toko_id',
            'transaksis.barang_id',
            'barangs.nama as nama_barang',
            'barangs.harga',
            'barangs.gambar',
            'barangs.deskripsi',
            'transaksis.user_id',
            'users.nama as nama_pembeli',
            'users.email',
            'users.no_telp',
            'users.alamat',
            'users.email',
            'transaksis.status',
            'transaksis.kode_pembayaran')
        ->leftjoin('barangs','barangs.id','transaksis.barang_id')
        ->leftjoin('users','users.id','transaksis.user_id')
        ->where('barangs.toko_id',$request->toko_id)
        ->where('transaksis.status',$request->status)
        ->groupBy(
            'transaksis.id',
            'barangs.toko_id',
            'transaksis.barang_id',
            'barangs.nama',
            'barangs.harga',
            'barangs.gambar',
            'barangs.deskripsi',
            'transaksis.user_id',
            'users.nama',
            'users.email',
            'users.no_telp',
            'users.alamat',
            'users.email',
            'transaksis.status',
            'transaksis.kode_pembayaran'
        )
        ->orderBy('transaksis.user_id','asc')
        ->get();

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
    public function update(Request $request)
    {
        $kode=$this->generateRandomString(5);
        Transaksi::whereIn('id',$request->id)->update([
            'status'=>$request->status,
            'kode_pembayaran'=>$kode,
        ]);
        $data=[
            'status'=>$request->status,
            'kode_pembayaran'=>$request->kode_pembayaran
        ];
        return response()->json(['data'=>$kode],200);
    }

    public function updateById(Request $request)
    {
        Transaksi::where('id',$request->transaksi_id)->update([
            'status'=>$request->status,
        ]);
        return response()->json(['data'=>$request->status],200);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'id' => 'required', 
        ]);
        if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        return response()->json(Transaksi::where('id',$request->id)->delete(),200);
    }

    private function generateRandomString($length = 10) {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        return $randomString;
    }
}
