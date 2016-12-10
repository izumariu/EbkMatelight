mkdir net
cp -r out/production/BottleDisplayClient/net/hermlon net
jar cfm MateLights.jar manifest.txt net
rm -r net
chmod +x MateLights.jar