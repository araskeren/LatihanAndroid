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
    Route::get('toko/user','API\TokoController@showByUser');
    Route::get('toko/detail','API\TokoController@show');
    Route::post('toko','API\TokoController@store');

    Route::get('barang','API\BarangController@index');
    Route::get('barang/detail/','API\BarangController@detail');
    Route::get('barang/user/','API\BarangController@showByUser');
    Route::post('barang/toko/','API\BarangController@show');
    Route::post('barang/create','API\BarangController@store');
    Route::post('barang/edit','API\BarangController@update');
    Route::post('barang/delete','API\BarangController@destroy');

    Route::get('transaksi','API\TransaksiController@index');
    Route::get('transaksi/user/','API\TransaksiController@show');
    Route::post('transaksi/toko/get-all','API\TransaksiController@showByToko');
    Route::post('transaksi/update/','API\TransaksiController@update');
    Route::post('transaksi/update/by-id','API\TransaksiController@updateById');
    Route::post('transaksi','API\TransaksiController@store');
    Route::delete('transaksi','API\TransaksiController@destroy');
    
});