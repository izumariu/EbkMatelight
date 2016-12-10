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
0.upto(255){|r|40.times{|led|ws.set(led,r,0,0)};ws.show;sleep 0.01}
loop do
	0.upto(255){|g|40.times{|led|ws.set(led,255,g,0)};ws.show;sleep 0.01}
	255.downto(0){|r|40.times{|led|ws.set(led,r,255,0)};ws.show;sleep 0.01}
	0.upto(255){|b|40.times{|led|ws.set(led,0,255,b)};ws.show;sleep 0.01}
	255.downto(0){|g|40.times{|led|ws.set(led,0,g,255)};ws.show;sleep 0.01}
	0.upto(255){|r|40.times{|led|ws.set(led,r,0,255)};ws.show;sleep 0.01}
	255.downto(0){|b|40.times{|led|ws.set(led,255,0,b)};ws.show;sleep 0.01}
end
