@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="">
            <div class="card">
                <div class="card-header">Daftar Toko | <a href="{{route('toko.create')}}" class="btn btn-primary">TAMBAH TOKO</a></div>

                <div class="card-body">
                    @if(Auth::user()->level=='9')
                    <table>
                        <thead>
                            <tr>
                                <th>LOGO</th>
                                <th>TOKO</th>
                                <th>ALAMAT TOKO</th>
                                <th>PEMILIK</th>
                                <th>NO TELP</th>
                                <th>EMAIL</th>
                                <th>ALAMAT PEMILIK</th>
                                <th>CREATED</th>
                                <th>UPDATE</th>
                                <th>AKSI</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($data as $i)
                            <tr>
                                <td><img src="{{$i->gambar}}" alt="" class="gambar-preview"></td>
                                <td>{{$i->nama_toko}}</td>
                                <td>{{$i->alamat_toko}}</td>
                                <td>{{$i->nama_pemilik}}</td>
                                <td>{{$i->no_telp}}</td>
                                <td>{{$i->email}}</td>
                                <td>{{$i->alamat_pemilik}}</td>
                                <td>{{$i->created_at}}</td>
                                <td>{{$i->updated_at}}</td>
                                <td>
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item dropdown">
                                            <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                                            <span class="caret"></span></a>
                                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                                <a class="dropdown-item" href="{{ route('barang.index',[
                                                'nama_toko'=>$i->nama_toko,'nama_pemilik'=>$i->nama_pemilik,'toko_id'=>$i->id]) }}">PRODUK</a>
                                                <a class="dropdown-item" href="{{ route('toko.edit',[
                                                'toko_id'=>$i->id]) }}">UBAH</a>
                                                <a class="dropdown-item" href="{{ route('toko.delete',[
                                                'toko_id'=>$i->id]) }}">HAPUS</a>
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
