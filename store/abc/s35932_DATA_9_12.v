//Converted to Combinational (Partial output: DATA_9_12) , Module name: s35932_DATA_9_12 , Timestamp: 2018-12-03T15:51:10.622009 
module s35932_DATA_9_12 ( TM0, WX523, WX747, WX875, WX683, WX811, DATA_9_12 );
input TM0, WX523, WX747, WX875, WX683, WX811;
output DATA_9_12;
wire n5697, n5539, n5698, n5696_1, n5693, n5695, n5692, n5694;
OAI21X1  g0160(.A0(n5698), .A1(n5539), .B0(n5697), .Y(DATA_9_12));
OR2X1    g0158(.A(n5696_1), .B(TM0), .Y(n5697));
INVX1    g0000(.A(TM0), .Y(n5539));
XOR2X1   g0159(.A(n5696_1), .B(WX523), .Y(n5698));
XOR2X1   g0157(.A(n5695), .B(n5693), .Y(n5696_1));
XOR2X1   g0154(.A(n5692), .B(WX747), .Y(n5693));
XOR2X1   g0156(.A(WX875), .B(n5694), .Y(n5695));
XOR2X1   g0153(.A(WX683), .B(TM0), .Y(n5692));
INVX1    g0155(.A(WX811), .Y(n5694));

endmodule
