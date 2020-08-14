//Converted to Combinational (Partial output: n109) , Module name: s444_n109 
module s444_n109 ( G17, G19, G20, G23, G21, G22, G0, G24, n109 );
input G17, G19, G20, G23, G21, G22, G0, G24;
output n109;
wire n155, n152, n93, n135, n108, n153, n154, n132, n134, n129, n91, n142, n119_1, n131, n130, n133, n128;
OAI22X1  g84(.A0(n135), .A1(n93), .B0(n152), .B1(n155), .Y(n109));
AOI21X1  g83(.A0(n154), .A1(n153), .B0(n108), .Y(n155));
AND2X1   g80(.A(n134), .B(n132), .Y(n152));
INVX1    g21(.A(G17), .Y(n93));
AOI21X1  g63(.A0(n134), .A1(n132), .B0(n129), .Y(n135));
OR2X1    g36(.A(G20), .B(G19), .Y(n108));
NAND4X1  g81(.A(n142), .B(G21), .C(n91), .D(G23), .Y(n153));
NAND3X1  g82(.A(G22), .B(n119_1), .C(n91), .Y(n154));
NAND4X1  g60(.A(G21), .B(n130), .C(G19), .D(n131), .Y(n132));
NOR2X1   g62(.A(n133), .B(G0), .Y(n134));
NOR4X1   g57(.A(n128), .B(n119_1), .C(G0), .D(n108), .Y(n129));
INVX1    g19(.A(G0), .Y(n91));
INVX1    g70(.A(G22), .Y(n142));
INVX1    g47(.A(G21), .Y(n119_1));
NOR2X1   g59(.A(G23), .B(G22), .Y(n131));
INVX1    g58(.A(G20), .Y(n130));
INVX1    g61(.A(G24), .Y(n133));
INVX1    g56(.A(G23), .Y(n128));

endmodule
