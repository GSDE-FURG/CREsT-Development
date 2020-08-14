//Converted to Combinational (Partial output: n64) , Module name: s400_n64
module s400_n64 ( C3_Q3, C3_Q1, C3_Q0, C3_Q2, CLR, TESTL, UC_8, UC_9, UC_10, UC_11, UC_16, UC_17, UC_18, UC_19, n64 );
input C3_Q3, C3_Q1, C3_Q0, C3_Q2, CLR, TESTL, UC_8, UC_9, UC_10, UC_11, UC_16, UC_17, UC_18, UC_19;
output n64;
wire n123, n126, n128, n122, n93, n86, n121, n124, n125, n74_1, n127, n117, n120, n96, n114_1, n115, n116, n118, n119_1;
NOR3X1   g57(.A(n128), .B(n126), .C(n123), .Y(n64));
NOR4X1   g51(.A(n121), .B(n86), .C(n93), .D(n122), .Y(n123));
AOI21X1  g54(.A0(n125), .A1(n124), .B0(C3_Q3), .Y(n126));
OAI21X1  g56(.A0(n127), .A1(n121), .B0(n74_1), .Y(n128));
NAND2X1  g50(.A(C3_Q0), .B(C3_Q1), .Y(n122));
INVX1    g21(.A(C3_Q3), .Y(n93));
INVX1    g14(.A(C3_Q2), .Y(n86));
NAND2X1  g49(.A(n120), .B(n117), .Y(n121));
AND2X1   g52(.A(n120), .B(n117), .Y(n124));
NOR2X1   g53(.A(n122), .B(n86), .Y(n125));
INVX1    g02(.A(CLR), .Y(n74_1));
OAI21X1  g55(.A0(n96), .A1(C3_Q2), .B0(C3_Q3), .Y(n127));
OAI21X1  g45(.A0(n116), .A1(n115), .B0(n114_1), .Y(n117));
NOR2X1   g48(.A(n119_1), .B(n118), .Y(n120));
OR2X1    g24(.A(C3_Q0), .B(C3_Q1), .Y(n96));
INVX1    g42(.A(TESTL), .Y(n114_1));
INVX1    g43(.A(UC_8), .Y(n115));
NOR3X1   g44(.A(UC_11), .B(UC_10), .C(UC_9), .Y(n116));
INVX1    g46(.A(UC_16), .Y(n118));
NOR3X1   g47(.A(UC_19), .B(UC_18), .C(UC_17), .Y(n119_1));

endmodule
