<?php

namespace App\Http\Controllers;

use Validator;
use Auth;
use Illuminate\Http\Request;
use App\User;
class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $data=User::orderBy('created_at','desc')->get();
        return view('user.index',compact('data'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('user.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'nama' => 'required', 
            'username' => 'required', 
            'email' => 'required',
            'no_telp'=>'required',
            'alamat'=> 'required',
            'password'=>'required',
            'password_confirmation'=>'required',
            'level'=>'required|integer',
        ]);
        if ($validator->fails()) { 
            return redirect()->back()->with(['error'=>$validator->errors()]);         
        }
        $input = $request->all();
        
        if($request->password == $request->password_confirmation){
            $input['password']=bcrypt($request->password);
        }else{
            return redirect()->back()->with(['error'=>$validator->errors()]);  
        }
        
        $barang = User::create($input);

        return redirect(route('user.index'));
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
    public function edit(Request $request)
    {
        $data=User::where('id',$request->user_id)->first();
        return view('user.edit',compact('data'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request)
    {
        $validator = Validator::make($request->all(), [ 
            'nama' => 'required', 
            'username' => 'required', 
            'email' => 'required',
            'no_telp'=>'required',
            'alamat'=> 'required',
            'password'=>'required',
            'password_confirmation'=>'required',
            'level'=>'required|integer',
            'id'=>'required'
        ]);
        if ($validator->fails()) { 
            return redirect()->back()->with(['error'=>$validator->errors()]);         
        }

        User::where('id',$request->id)->update([
            'nama'=>$request->nama,
            'username' => $request->username,
            'email' => $request->email,
            'no_telp'=>$request->no_telp,
            'alamat'=> $request->alamat,
            'password'=>bcrypt($request->password),
            'level'=>$request->level,
        ]);

        return redirect(route('user.index'));
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request)
    {
        User::where('id',$request->user_id)->delete();
        return redirect()->back();
    }
}
