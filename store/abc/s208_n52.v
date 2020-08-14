//Converted to Combinational (Partial output: n52) , Module name: s208_n52 , Timestamp: 2018-12-03T15:51:01.368075 
module s208_n52 ( Y_7, Y_4, Y_3, Y_6, Y_5, Y_2, Y_1, Clear, n52 );
input Y_7, Y_4, Y_3, Y_6, Y_5, Y_2, Y_1, Clear;
output n52;
wire n85, n78, n83, n84, n71, n64, n79, n67, n40, n41;
OAI22X1  g49(.A0(n83), .A1(Y_7), .B0(n78), .B1(n85), .Y(n52));
NAND2X1  g48(.A(n84), .B(Y_7), .Y(n85));
NAND4X1  g41(.A(Y_3), .B(Y_4), .C(n64), .D(n71), .Y(n78));
NAND2X1  g46(.A(n79), .B(Y_6), .Y(n83));
NAND2X1  g47(.A(Y_5), .B(Y_6), .Y(n84));
AND2X1   g34(.A(Y_1), .B(Y_2), .Y(n71));
INVX1    g27(.A(Clear), .Y(n64));
NOR4X1   g42(.A(n41), .B(n40), .C(Clear), .D(n67), .Y(n79));
NAND3X1  g30(.A(Y_1), .B(Y_2), .C(Y_3), .Y(n67));
INVX1    g03(.A(Y_4), .Y(n40));
INVX1    g04(.A(Y_5), .Y(n41));

endmodule
