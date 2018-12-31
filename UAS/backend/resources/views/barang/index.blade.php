@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Daftar Barang | {{$nama_toko}} | Pemilik : {{$nama_pemilik}} | <a href="{{route('barang.create')}}" class="btn btn-primary">TAMBAH BARANG</a></div>

                <div class="card-body">
                    @if(Auth::user()->level=='9')
                    <table class="col-md-12">
                        <thead>
                            <tr>
                                <th>GAMBAR</th>
                                <th>BARANG</th>
                                <th>HARGA</th>
                                <th>DESKRIPSI</th>
                                <th>CREATED</th>
                                <th>UPDATE</th>
                                <th>AKSI</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($data as $i)
                            <tr>
                            
                                <td><img src="{{$i->gambar}}" alt="" class="gambar-preview"></td>
                                <td>{{$i->nama}}</td>
                                <td>{{$i->harga}}</td>
                                <td>{{$i->deskripsi}}</td>
                                <td>{{$i->created_at}}</td>
                                <td>{{$i->updated_at}}</td>
                                <td>
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item dropdown">
                                            <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                                            <span class="caret"></span></a>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                                <a class="dropdown-item" href="{{ route('barang.edit',[
                                                'barang_id'=>$i->id]) }}">UBAH</a>
                                                <a class="dropdown-item" href="{{ route('barang.delete',[
                                                'barang_id'=>$i->id]) }}">HAPUS</a>
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
