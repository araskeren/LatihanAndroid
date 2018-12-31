@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="">
            <div class="card">
                <div class="card-header">Daftar Transaksi</div>

                <div class="card-body">
                    @if(Auth::user()->level=='9')
                    <table>
                        <thead>
                            <tr>
                                <th>TOKO</th>
                                <th>BARANG</th>
                                <th>HARGA</th>
                                <th>PEMBELI</th>
                                <th>NO TELP</th>
                                <th>ALAMAT</th>
                                <th>STATUS</th>
                                <th>KODE</th>
                                <th>CREATED</th>
                                <th>UPDATE</th>
                                <th>AKSI</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($data as $i)
                            <tr>
                                <td>{{$i->nama_toko}}</td>
                                <td>{{$i->nama_barang}}</td>
                                <td>{{$i->harga}}</td>
                                <td>{{$i->nama_pembeli}}</td>
                                <td>{{$i->no_telp}}</td>
                                <td>{{$i->alamat}}</td>
                                <td>{{$i->status}}</td>
                                <td>{{$i->kode_pembayaran}}</td>
                                <td>{{$i->created_at}}</td>
                                <td>{{$i->updated_at}}</td>
                                <td>
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item dropdown">
                                            <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                                            <span class="caret"></span></a>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                                <a class="dropdown-item" href="{{ route('transaksi.ubah.status',[
                                                'status'=>'pembayaran','id_transaksi'=>$i->id]) }}">PEMBAYARAN</a>
                                                <a class="dropdown-item" href="{{ route('transaksi.ubah.status',[
                                                'status'=>'menunggu pengiriman','id_transaksi'=>$i->id]) }}">MENUNGGU PENGIRIMAN</a>
                                                <a class="dropdown-item" href="{{ route('transaksi.ubah.status',[
                                                'status'=>'terkirim','id_transaksi'=>$i->id]) }}">TERKIRIM</a>
                                                <a class="dropdown-item" href="{{ route('transaksi.ubah.status',[
                                                'status'=>'selesai','id_transaksi'=>$i->id]) }}">SELESAI</a>
                                            </div>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                            @endforeach
                        </tbody>
                    </table>
                    @endif
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
