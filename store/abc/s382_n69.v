//Converted to Combinational (Partial output: n69) , Module name: s382_n69
module s382_n69 ( C3_Q2, CLR, C3_Q3, C3_Q1, C3_Q0, TESTL, UC_8, UC_9, UC_10, UC_11, UC_16, UC_17, UC_18, UC_19, n69 );
input C3_Q2, CLR, C3_Q3, C3_Q1, C3_Q0, TESTL, UC_8, UC_9, UC_10, UC_11, UC_16, UC_17, UC_18, UC_19;
output n69;
wire n126, n128, n130, n74_1, n124, n125, n86, n79_1, n80, n129, n122, n118, n121, n96, n115, n116, n117, n119_1, n120;
NOR3X1   g59(.A(n130), .B(n128), .C(n126), .Y(n69));
OAI21X1  g54(.A0(n125), .A1(n124), .B0(n74_1), .Y(n126));
NOR4X1   g56(.A(n80), .B(n79_1), .C(n86), .D(n124), .Y(n128));
AOI21X1  g58(.A0(n122), .A1(n129), .B0(C3_Q2), .Y(n130));
INVX1    g02(.A(CLR), .Y(n74_1));
NAND2X1  g52(.A(n121), .B(n118), .Y(n124));
OAI21X1  g53(.A0(n96), .A1(C3_Q2), .B0(C3_Q3), .Y(n125));
INVX1    g14(.A(C3_Q2), .Y(n86));
INVX1    g07(.A(C3_Q1), .Y(n79_1));
INVX1    g08(.A(C3_Q0), .Y(n80));
AND2X1   g57(.A(n121), .B(n118), .Y(n129));
AND2X1   g50(.A(C3_Q0), .B(C3_Q1), .Y(n122));
OAI21X1  g46(.A0(n117), .A1(n116), .B0(n115), .Y(n118));
NOR2X1   g49(.A(n120), .B(n119_1), .Y(n121));
OR2X1    g24(.A(C3_Q0), .B(C3_Q1), .Y(n96));
INVX1    g43(.A(TESTL), .Y(n115));
INVX1    g44(.A(UC_8), .Y(n116));
NOR3X1   g45(.A(UC_11), .B(UC_10), .C(UC_9), .Y(n117));
INVX1    g47(.A(UC_16), .Y(n119_1));
NOR3X1   g48(.A(UC_19), .B(UC_18), .C(UC_17), .Y(n120));

endmodule
