@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Tambah Barang</div>

                <div class="card-body">
                    @if(Auth::user()->level=='9')
                    <form method="POST" action="{{ route('barang.store') }}" aria-label="{{ __('Register') }}">
                        @csrf

                        <div class="form-group row">
                            <label for="nama" class="col-md-2 col-form-label text-md-right">{{ __('Nama') }}</label>

                            <div class="col-md-8">
                                <input id="nama" type="text" class="form-control{{ $errors->has('nama') ? ' is-invalid' : '' }}" name="nama" value="{{ old('nama') }}" required autofocus>

                                @if ($errors->has('nama'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('nama') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="harga" class="col-md-2 col-form-label text-md-right">{{ __('Harga') }}</label>

                            <div class="col-md-8">
                                <input id="harga" type="number" class="form-control{{ $errors->has('harga') ? ' is-invalid' : '' }}" name="harga" value="{{ old('harga') }}" required autofocus>

                                @if ($errors->has('harga'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('harga') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="deskripsi" class="col-md-2 col-form-label text-md-right">{{ __('Deskripsi') }}</label>

                            <div class="col-md-8">
                                <input id="deskripsi" type="text" class="form-control{{ $errors->has('deskripsi') ? ' is-invalid' : '' }}" name="deskripsi" value="{{ old('deskripsi') }}" required autofocus>

                                @if ($errors->has('deskripsi'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('deskripsi') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="gambar" class="col-md-2 col-form-label text-md-right">{{ __('Link Gambar') }}</label>

                            <div class="col-md-8">
                                <input id="gambar" type="text" class="form-control{{ $errors->has('gambar') ? ' is-invalid' : '' }}" name="gambar" value="{{ old('gambar') }}" required autofocus>

                                @if ($errors->has('gambar'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('gambar') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="toko_id" class="col-md-2 col-form-label text-md-right">{{ __('Toko') }}</label>

                            <div class="col-md-8">
                                <select name="toko_id" id="toko_id" value="{{ old('toko_id') }}" class="form-control{{ $errors->has('toko_id') ? ' is-invalid' : '' }}" required>
                                    @foreach($toko as $i)
                                        <option value="{{$i->id}}">{{$i->nama}} | {{$i->nama_pemilik}}</option>
                                    @endforeach
                                </select>
                                @if ($errors->has('toko_id'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('toko_id') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-2">
                                <button type="submit" class="btn btn-primary">
                                    {{ __('Tambah') }}
                                </button>
                            </div>
                        </div>
                    </form>
                    @endif
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
