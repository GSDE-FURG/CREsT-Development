//Converted to Combinational , Module name: b02 , Timestamp: 2018-11-12T14:26:28.013333 
module b02 ( clock, LINEA, STATO_REG_2_, STATO_REG_1_, STATO_REG_0_, U_REG, n9, n14, n19 );
input clock, LINEA, STATO_REG_2_, STATO_REG_1_, STATO_REG_0_;
output U_REG, n9, n14, n19;
wire n14_1, n16, n17, n18, n19_1, n20, n21, n23, n24, n26, n27;
INVX1    g00(.A(STATO_REG_2_), .Y(n14_1));
NOR3X1   g01(.A(STATO_REG_0_), .B(STATO_REG_1_), .C(n14_1), .Y(U_REG));
INVX1    g02(.A(STATO_REG_1_), .Y(n16));
INVX1    g03(.A(LINEA), .Y(n17));
INVX1    g04(.A(STATO_REG_0_), .Y(n18));
OAI21X1  g05(.A0(STATO_REG_2_), .A1(n17), .B0(n18), .Y(n19_1));
AOI21X1  g06(.A0(STATO_REG_2_), .A1(n17), .B0(n19_1), .Y(n20));
OAI21X1  g07(.A0(STATO_REG_2_), .A1(LINEA), .B0(STATO_REG_0_), .Y(n21));
OAI21X1  g08(.A0(n20), .A1(n16), .B0(n21), .Y(n9));
NAND3X1  g09(.A(n18), .B(STATO_REG_1_), .C(n14_1), .Y(n23));
AOI21X1  g10(.A0(n16), .A1(n17), .B0(STATO_REG_2_), .Y(n24));
OAI21X1  g11(.A0(n24), .A1(n18), .B0(n23), .Y(n14));
OR2X1    g12(.A(STATO_REG_2_), .B(LINEA), .Y(n26));
AOI21X1  g13(.A0(n14_1), .A1(LINEA), .B0(n18), .Y(n27));
OAI22X1  g14(.A0(n26), .A1(STATO_REG_0_), .B0(STATO_REG_1_), .B1(n27), .Y(n19));
endmodule
