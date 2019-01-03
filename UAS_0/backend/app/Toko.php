<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Laravel\Passport\HasApiTokens;
use Illuminate\Notifications\Notifiable;
class Toko extends Model
{
    use HasApiTokens, Notifiable;
    protected $fillable = [
        'nama', 'alamat', 'pemilik_id','create_id'
    ];
}
