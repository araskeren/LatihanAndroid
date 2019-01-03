@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Edit Toko</div>

                <div class="card-body">
                    @if(Auth::user()->level=='9')
                    <form method="POST" action="{{ route('toko.update') }}" aria-label="{{ __('Register') }}">
                        @csrf

                        <div class="form-group row">
                            <label for="nama" class="col-md-2 col-form-label text-md-right">{{ __('Nama') }}</label>

                            <div class="col-md-8">
                                <input id="nama" type="text" class="form-control{{ $errors->has('nama') ? ' is-invalid' : '' }}" name="nama" value="{{ $data->nama }}" required autofocus>

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
                                <input id="alamat" type="text" class="form-control{{ $errors->has('alamat') ? ' is-invalid' : '' }}" name="alamat" value="{{ $data->alamat }}" required autofocus>

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
                                <input id="cover" type="text" class="form-control{{ $errors->has('cover') ? ' is-invalid' : '' }}" name="cover" value="{{ $data->cover }}" required autofocus>

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
                                <select name="pemilik_id" id="pemilik_id" class="form-control{{ $errors->has('pemilik_id') ? ' is-invalid' : '' }}" required>
                                    @foreach($user as $i)
                                        @if($i->id==$data->pemilik_id)
                                        <option value="{{$i->id}}" selected>{{$i->nama}}</option>
                                        @else
                                        <option value="{{$i->id}}">{{$i->nama}}</option>
                                        @endif
                                    @endforeach
                                </select>
                                @if ($errors->has('pemilik_id'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('pemilik_id') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <input type="hidden" name="id" value="{{$_GET['toko_id']}}">

                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-2">
                                <button type="submit" class="btn btn-primary">
                                    {{ __('Update') }}
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
