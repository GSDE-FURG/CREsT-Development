//Converted to Combinational , Module name: s27 
module s27 ( clock, G0, G1, G2, G3, G5, G6, G7, G17, n11, n16, n21 );
input clock, G0, G1, G2, G3, G5, G6, G7;
output G17, n11, n16, n21;
wire n14, n15, n16g1, n17, n18, n19;
INVX1    g0(.A(G0), .Y(n14));
AOI21X1  g1(.A0(G6), .A1(n14), .B0(G3), .Y(n15));
INVX1    g2(.A(G1), .Y(n16g1));
INVX1    g3(.A(G7), .Y(n17));
AOI22X1  g4(.A0(G6), .A1(n14), .B0(n16g1), .B1(n17), .Y(n18));
OR2X1    g5(.A(n18), .B(G5), .Y(n19));
OR2X1    g6(.A(n19), .B(n15), .Y(G17));
NOR3X1   g7(.A(n18), .B(n15), .C(G5), .Y(n16));
NOR2X1   g8(.A(n16), .B(n14), .Y(n11));
AOI21X1  g9(.A0(n17), .A1(n16g1), .B0(G2), .Y(n21));
endmodule
