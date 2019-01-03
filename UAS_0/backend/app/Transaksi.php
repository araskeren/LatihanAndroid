<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;

class Transaksi extends Model
{
    use HasApiTokens, Notifiable;
    protected $fillable = [
        'user_id', 'barang_id'
    ];
}
