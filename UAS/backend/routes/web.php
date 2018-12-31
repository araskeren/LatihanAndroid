<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::group(['middleware' => 'auth'], function(){
    Route::get('/toko', 'TokoController@index')->name('toko.index');
    Route::get('/toko/create', 'TokoController@create')->name('toko.create');
    Route::post('/toko/create', 'TokoController@store')->name('toko.store');
    Route::get('/toko/edit', 'TokoController@edit')->name('toko.edit');
    Route::post('/toko/edit', 'TokoController@update')->name('toko.update');
    Route::get('/toko/delete', 'TokoController@destroy')->name('toko.delete');

    Route::get('/barang', 'BarangController@index')->name('barang.index');
    Route::get('/barang/create', 'BarangController@create')->name('barang.create');
    Route::post('/barang/create', 'BarangController@store')->name('barang.store');
    Route::get('/barang/edit', 'BarangController@edit')->name('barang.edit');
    Route::post('/barang/edit', 'BarangController@update')->name('barang.update');
    Route::get('/barang/delete', 'BarangController@destroy')->name('barang.delete');

    Route::get('/user', 'UserController@index')->name('user.index');
    Route::get('/user/create', 'UserController@create')->name('user.create');
    Route::post('/user/create', 'UserController@store')->name('user.store');
    Route::get('/user/edit', 'UserController@edit')->name('user.edit');
    Route::post('/user/edit', 'UserController@update')->name('user.update');
    Route::get('/user/delete', 'UserController@destroy')->name('user.delete');

    Route::get('/transaksi', 'TransaksiController@index')->name('transaksi.index');
    Route::get('/transaksi/ubah-status', 'TransaksiController@updateStatus')->name('transaksi.ubah.status');
});
