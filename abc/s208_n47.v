//Converted to Combinational (Partial output: n47) , Module name: s208_n47 , Timestamp: 2018-12-03T15:51:01.367075 
module s208_n47 ( Y_7, Y_6, Y_4, Y_3, Y_8, Clear, Y_2, Y_1, Y_5, n47 );
input Y_7, Y_6, Y_4, Y_3, Y_8, Clear, Y_2, Y_1, Y_5;
output n47;
wire n80, n78, n81, n79, n37_1, n71, n64, n38, n67, n40, n41;
OAI21X1  g45(.A0(n81), .A1(n78), .B0(n80), .Y(n47));
NAND4X1  g43(.A(Y_6), .B(Y_7), .C(n37_1), .D(n79), .Y(n80));
NAND4X1  g41(.A(Y_3), .B(Y_4), .C(n64), .D(n71), .Y(n78));
NAND2X1  g44(.A(n38), .B(Y_8), .Y(n81));
NOR4X1   g42(.A(n41), .B(n40), .C(Clear), .D(n67), .Y(n79));
INVX1    g00(.A(Y_8), .Y(n37_1));
AND2X1   g34(.A(Y_1), .B(Y_2), .Y(n71));
INVX1    g27(.A(Clear), .Y(n64));
NAND3X1  g01(.A(Y_5), .B(Y_6), .C(Y_7), .Y(n38));
NAND3X1  g30(.A(Y_1), .B(Y_2), .C(Y_3), .Y(n67));
INVX1    g03(.A(Y_4), .Y(n40));
INVX1    g04(.A(Y_5), .Y(n41));

endmodule
