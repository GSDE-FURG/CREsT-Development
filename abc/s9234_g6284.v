//Converted to Combinational (Partial output: g6284) , Module name: s9234_g6284 , Timestamp: 2018-12-03T15:51:03.628264 
module s9234_g6284 ( g1, g2, g3, g6, g41, g22, g676, g675, g33, g29, g25, g19, g11, g15, g7, g48, g10, g18, g14, g24, g28, g6284 );
input g1, g2, g3, g6, g41, g22, g676, g675, g33, g29, g25, g19, g11, g15, g7, g48, g10, g18, g14, g24, g28;
output g6284;
wire n729, n736_1, n725, n715, n727, n732, n735, n714, n724, n726_1, n730, n731_1, n733, n734, n716_1, n723, n719, n722, n717, n718, n720, n721_1;
NAND2X1  g0029(.A(n736_1), .B(n729), .Y(g6284));
NOR3X1   g0021(.A(n727), .B(n715), .C(n725), .Y(n729));
XOR2X1   g0028(.A(n735), .B(n732), .Y(n736_1));
INVX1    g0017(.A(g41), .Y(n725));
OR2X1    g0007(.A(n714), .B(g22), .Y(n715));
OAI21X1  g0019(.A0(n726_1), .A1(n724), .B0(g676), .Y(n727));
XOR2X1   g0024(.A(n731_1), .B(n730), .Y(n732));
XOR2X1   g0027(.A(n734), .B(n733), .Y(n735));
INVX1    g0006(.A(g675), .Y(n714));
AND2X1   g0016(.A(n723), .B(n716_1), .Y(n724));
OAI21X1  g0018(.A0(n723), .A1(n716_1), .B0(n725), .Y(n726_1));
XOR2X1   g0022(.A(g29), .B(g33), .Y(n730));
XOR2X1   g0023(.A(g19), .B(g25), .Y(n731_1));
XOR2X1   g0025(.A(g15), .B(g11), .Y(n733));
XOR2X1   g0026(.A(g7), .B(g3), .Y(n734));
INVX1    g0008(.A(g48), .Y(n716_1));
XOR2X1   g0015(.A(n722), .B(n719), .Y(n723));
XOR2X1   g0011(.A(n718), .B(n717), .Y(n719));
XOR2X1   g0014(.A(n721_1), .B(n720), .Y(n722));
XOR2X1   g0009(.A(g1), .B(g2), .Y(n717));
XOR2X1   g0010(.A(g10), .B(g6), .Y(n718));
XOR2X1   g0012(.A(g14), .B(g18), .Y(n720));
XOR2X1   g0013(.A(g28), .B(g24), .Y(n721_1));

endmodule
