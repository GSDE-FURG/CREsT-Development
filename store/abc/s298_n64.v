//Converted to Combinational (Partial output: n64) , Module name: s298_n64
module s298_n64 ( G19, G10, G13, G14, G15, G22, G12, G11, n64 );
input G19, G10, G13, G14, G15, G22, G12, G11;
output n64;
wire n102, n106, n88, n53, n71, n82, n105, n87, n84_1, n85, n86, n81, n78, n80, n104, n61, n58, n68, n79_1, n59_1, n103;
NOR2X1   g56(.A(n106), .B(n102), .Y(n64));
NOR4X1   g51(.A(G19), .B(n71), .C(n53), .D(n88), .Y(n102));
MX2X1    g55(.A(G10), .B(n105), .S0(n82), .Y(n106));
AOI22X1  g37(.A0(n86), .A1(n85), .B0(n84_1), .B1(n87), .Y(n88));
INVX1    g02(.A(G13), .Y(n53));
INVX1    g20(.A(G14), .Y(n71));
OAI22X1  g31(.A0(n80), .A1(n78), .B0(G15), .B1(n81), .Y(n82));
AOI21X1  g54(.A0(n61), .A1(G14), .B0(n104), .Y(n105));
NAND4X1  g36(.A(G22), .B(n71), .C(G13), .D(n61), .Y(n87));
INVX1    g33(.A(G15), .Y(n84_1));
NOR2X1   g34(.A(G12), .B(n58), .Y(n85));
NOR3X1   g35(.A(G22), .B(G14), .C(n53), .Y(n86));
NOR4X1   g30(.A(n79_1), .B(G14), .C(n53), .D(n68), .Y(n81));
NAND2X1  g27(.A(n59_1), .B(G11), .Y(n78));
NAND3X1  g29(.A(n79_1), .B(n71), .C(G13), .Y(n80));
NAND2X1  g53(.A(n103), .B(n53), .Y(n104));
NOR2X1   g10(.A(G12), .B(G11), .Y(n61));
INVX1    g07(.A(G11), .Y(n58));
OR2X1    g17(.A(G12), .B(G11), .Y(n68));
INVX1    g28(.A(G22), .Y(n79_1));
INVX1    g08(.A(G12), .Y(n59_1));
NAND3X1  g52(.A(G19), .B(G14), .C(G12), .Y(n103));
endmodule
