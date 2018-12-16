<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('login', 'API\UserController@login');
Route::post('register', 'API\UserController@register');
Route::group(['middleware' => 'auth:api'], function(){
    Route::post('details', 'API\UserController@details');

    Route::get('toko','API\TokoController@index');
    Route::get('toko/detail','API\TokoController@show');
    Route::post('toko','API\TokoController@store');

    Route::get('barang','API\BarangController@index');
    Route::get('barang/detail/','API\BarangController@detail');
    Route::get('barang/toko/','API\BarangController@show');
    Route::post('barang','API\BarangController@store');

    Route::get('transaksi','API\TransaksiController@index');
    Route::get('transaksi/user/','API\TransaksiController@show');
    Route::post('transaksi','API\TransaksiController@store');
    Route::delete('transaksi','API\TransaksiController@destroy');
    
});