-module(ebklights_fwd).
-export([listen/0, start/0, stop/0]).
-define(proc_name, ebklights).

listen() ->
	receive 
	Message ->
		{ok, Sock} = gen_tcp:connect("mariuszero",1337,[binary,{packet,0}]),
		ok = gen_tcp:send(Sock, Message),
		ok = gen_tcp:close(Sock),
		listen()
	end.

start() ->
	Pid = spawn(?MODULE, listen, []),
	register(?proc_name,Pid).

stop() -> unregister(?proc_name).