@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Tambah Toko</div>

                <div class="card-body">
                    @if(Auth::user()->level=='9')
                    <form method="POST" action="{{ route('toko.store') }}" aria-label="{{ __('Register') }}">
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
                            <label for="alamat" class="col-md-2 col-form-label text-md-right">{{ __('Alamat') }}</label>

                            <div class="col-md-8">
                                <input id="alamat" type="text" class="form-control{{ $errors->has('alamat') ? ' is-invalid' : '' }}" name="alamat" value="{{ old('alamat') }}" required autofocus>

                                @if ($errors->has('alamat'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('alamat') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="cover" class="col-md-2 col-form-label text-md-right">{{ __('Link Gambar') }}</label>

                            <div class="col-md-8">
                                <input id="cover" type="text" class="form-control{{ $errors->has('cover') ? ' is-invalid' : '' }}" name="cover" value="{{ old('cover') }}" required autofocus>

                                @if ($errors->has('cover'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('cover') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="pemilik_id" class="col-md-2 col-form-label text-md-right">{{ __('Pemilik') }}</label>

                            <div class="col-md-8">
                                <select name="pemilik_id" id="pemilik_id" value="{{ old('pemilik_id') }}" class="form-control{{ $errors->has('pemilik_id') ? ' is-invalid' : '' }}" required>
                                    @foreach($user as $i)
                                        <option value="{{$i->id}}">{{$i->nama}}</option>
                                    @endforeach
                                </select>
                                @if ($errors->has('pemilik_id'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('pemilik_id') }}</strong>
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
