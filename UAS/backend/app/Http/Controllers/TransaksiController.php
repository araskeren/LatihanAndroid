<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Transaksi;
class TransaksiController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
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
            'transaksis.kode_pembayaran',
            'transaksis.created_at',
            'transaksis.updated_at',
            'tokos.nama as nama_toko')
        ->leftjoin('barangs','barangs.id','transaksis.barang_id')
        ->leftjoin('tokos','tokos.id','barangs.toko_id')
        ->leftjoin('users','users.id','transaksis.user_id')
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
            'transaksis.kode_pembayaran',
            'transaksis.created_at',
            'transaksis.updated_at',
            'tokos.nama'
        )
        ->orderBy('transaksis.user_id','asc')
        ->get();
        return view('transaksi.index',compact('data'));
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
        //
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

    public function updateStatus(Request $request){
        $data=Transaksi::where('id',$request->id_transaksi)->update([
            'status'=>$request->status
        ]);

        return redirect()->back();
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
