<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;

class Barang extends Model
{
    use HasApiTokens, Notifiable;
    protected $fillable = [
        'nama', 'harga', 'deskripsi','toko_id','gambar','user_id'
    ];
}
