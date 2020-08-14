//Converted to Combinational (Partial output: DATA_9_18) , Module name: s35932_DATA_9_18 , Timestamp: 2018-12-03T15:51:10.615013 
module s35932_DATA_9_18 ( TM0, WX511, WX735, WX863, TM1, WX671, WX799, DATA_9_18 );
input TM0, WX511, WX735, WX863, TM1, WX671, WX799;
output DATA_9_18;
wire n5649, n5539, n5650, n5648, n5645, n5647, n5644, n5646_1;
OAI21X1  g0112(.A0(n5650), .A1(n5539), .B0(n5649), .Y(DATA_9_18));
OR2X1    g0110(.A(n5648), .B(TM0), .Y(n5649));
INVX1    g0000(.A(TM0), .Y(n5539));
XOR2X1   g0111(.A(n5648), .B(WX511), .Y(n5650));
XOR2X1   g0109(.A(n5647), .B(n5645), .Y(n5648));
XOR2X1   g0106(.A(n5644), .B(WX735), .Y(n5645));
XOR2X1   g0108(.A(WX863), .B(n5646_1), .Y(n5647));
XOR2X1   g0105(.A(WX671), .B(TM1), .Y(n5644));
INVX1    g0107(.A(WX799), .Y(n5646_1));

endmodule
