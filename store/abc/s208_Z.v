//Converted to Combinational (Partial output: Z) , Module name: s208_Z , Timestamp: 2018-12-03T15:51:01.370075 
module s208_Z ( C_5, Y_5, Y_4, Y_6, Y_7, C_7, C_6, Y_1, Y_3, Y_2, C_4, C_8, Y_8, X, C_3, C_2, C_0, C_1, Z );
input C_5, Y_5, Y_4, Y_6, Y_7, C_7, C_6, Y_1, Y_3, Y_2, C_4, C_8, Y_8, X, C_3, C_2, C_0, C_1;
output Z;
wire n62_1, n45, n46, n56, n58, n60, n61, n44, n40, n41, n43, n47_1, n55, n57_1, n59, n42_1, n49, n51, n54, n48, n50, n52_1, n53;
NAND4X1  g26(.A(n56), .B(n46), .C(n45), .D(n62_1), .Y(Z));
OAI21X1  g25(.A0(n61), .A1(n60), .B0(n58), .Y(n62_1));
NAND4X1  g08(.A(n43), .B(n41), .C(n40), .D(n44), .Y(n45));
NAND4X1  g09(.A(Y_5), .B(n40), .C(C_5), .D(n43), .Y(n46));
AND2X1   g19(.A(n55), .B(n47_1), .Y(n56));
NOR4X1   g21(.A(Y_5), .B(Y_6), .C(Y_4), .D(n57_1), .Y(n58));
NOR2X1   g23(.A(n59), .B(Y_7), .Y(n60));
AND2X1   g24(.A(Y_7), .B(C_7), .Y(n61));
AND2X1   g07(.A(Y_6), .B(C_6), .Y(n44));
INVX1    g03(.A(Y_4), .Y(n40));
INVX1    g04(.A(Y_5), .Y(n41));
NOR4X1   g06(.A(Y_2), .B(Y_3), .C(n42_1), .D(Y_1), .Y(n43));
NAND3X1  g10(.A(n43), .B(Y_4), .C(C_4), .Y(n47_1));
NOR3X1   g18(.A(n54), .B(n51), .C(n49), .Y(n55));
OR4X1    g20(.A(Y_2), .B(Y_3), .C(n42_1), .D(Y_1), .Y(n57_1));
NAND2X1  g22(.A(Y_8), .B(C_8), .Y(n59));
INVX1    g05(.A(X), .Y(n42_1));
NOR4X1   g12(.A(Y_1), .B(Y_2), .C(n42_1), .D(n48), .Y(n49));
NOR3X1   g14(.A(n50), .B(Y_1), .C(n42_1), .Y(n51));
NAND2X1  g17(.A(n53), .B(n52_1), .Y(n54));
NAND2X1  g11(.A(Y_3), .B(C_3), .Y(n48));
NAND2X1  g13(.A(Y_2), .B(C_2), .Y(n50));
NAND2X1  g15(.A(C_0), .B(X), .Y(n52_1));
NAND3X1  g16(.A(Y_1), .B(C_1), .C(X), .Y(n53));

endmodule
