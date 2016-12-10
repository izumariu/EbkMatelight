require 'ws2812'
ws = Ws2812::Basic.new(40,18,255)
ws.open
40.times{|led|ws.set(led,0,0,0)};ws.show
#gets
Signal.trap("INT") {
	until ws.brightness==0
		ws.brightness-=1
		ws.show
		sleep 0.005
	end
	exit(0)
}
loop do
	ws[Random.rand(40)] = Ws2812::Color.new(Random.rand(50..256),Random.rand(50..256),Random.rand(50..256))
	ws.show
	sleep 0.05
end
