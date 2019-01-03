<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request; 
use App\Http\Controllers\Controller; 
use App\User; 
use App\Toko;
use Illuminate\Support\Facades\Auth; 
use Validator;

class UserController extends Controller
{
    public $successStatus = 200;
    /** 
     * login api 
     * 
     * @return \Illuminate\Http\Response 
     */ 
    public function login(){ 
        if(Auth::attempt(['email' => request('email'), 'password' => request('password')])){ 
            $user = Auth::user(); 
            $toko_id = Toko::select('id')->where('pemilik_id',$user->id)->first();
            $user->toko_id=($toko_id)?$toko_id->id:0;
            $success['token'] =  $user->createToken('MyApp')-> accessToken; 
            $success['user'] = $user;
            return response()->json(['success' => $success], $this-> successStatus); 
        } 
        else{ 
            return response()->json(['error'=>'Unauthorised'], 401); 
        } 
    }
    /** 
     * Register api 
     * 
     * @return \Illuminate\Http\Response 
     */ 
    public function register(Request $request) 
    { 
        $validator = Validator::make($request->all(), [ 
            'username'=>'required',
            'nama' => 'required', 
            'email' => 'required|email', 
            'password' => 'required', 
            'c_password' => 'required|same:password',
            'no_telp'=>'required',
            'alamat'=>'required', 
        ]);
        if ($validator->fails()) { 
            return response()->json(['error'=>$validator->errors()], 401);            
        }
        $input = $request->all(); 
        $input['password'] = bcrypt($input['password']); 
        $input['level']=1;
        $user = User::create($input); 
        
        $success['token'] =  $user->createToken('MyApp')-> accessToken; 
        $success['nama'] =  $user->nama;
        return response()->json(['success'=>$success], $this-> successStatus); 
    }
    /** 
     * details api 
     * 
     * @return \Illuminate\Http\Response 
     */ 
    public function details(Request $request) 
    {
        $user = Auth::user(); 
        $request->header('Authorization','Bearer'.$request->Authorization);
        return response()->json(['user' => $user], $this-> successStatus); 
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
