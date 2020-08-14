//Converted to Combinational (Partial output: n75) , Module name: s1488_n75
module s1488_n75 ( v1, CLR, v7, v8, v2, v12, v9, v3, v11, v10, v0, v6, v5, v4, n75 );
input v1, CLR, v7, v8, v2, v12, v9, v3, v11, v10, v0, v6, v5, v4;
output n75;
wire n267, n382, n395, n45, n376, n381, n394, n386, n374, n375, n380, n103, n379, n393, n218, n392, n385, n72, n184, n383, n371, n373, n70_1, n324, n143, n64, n363, n83, n377, n378, n57, n63, n217, n387, n391, n390, n68, n384, n82, n104, n372, n69, n54, n111, n142, n47, n135, n154, n388, n389, n90;
AOI21X1  g351(.A0(n395), .A1(n382), .B0(n267), .Y(n75));
INVX1    g222(.A(CLR), .Y(n267));
OAI21X1  g337(.A0(n381), .A1(n376), .B0(n45), .Y(n382));
AOI21X1  g350(.A0(n386), .A1(v7), .B0(n394), .Y(n395));
INVX1    g000(.A(v7), .Y(n45));
AOI21X1  g331(.A0(n375), .A1(n374), .B0(v8), .Y(n376));
OAI21X1  g336(.A0(n379), .A1(n103), .B0(n380), .Y(n381));
OAI22X1  g349(.A0(n392), .A1(v2), .B0(n218), .B1(n393), .Y(n394));
OAI22X1  g341(.A0(n383), .A1(n184), .B0(n72), .B1(n385), .Y(n386));
OAI21X1  g329(.A0(n373), .A1(n371), .B0(v12), .Y(n374));
AOI22X1  g330(.A0(n143), .A1(n324), .B0(n70_1), .B1(v9), .Y(n375));
AOI22X1  g335(.A0(n83), .A1(n363), .B0(n64), .B1(v9), .Y(n380));
INVX1    g058(.A(v2), .Y(n103));
AOI21X1  g334(.A0(n378), .A1(n72), .B0(n377), .Y(n379));
AOI22X1  g348(.A0(n63), .A1(v12), .B0(v3), .B1(n57), .Y(n393));
INVX1    g173(.A(n217), .Y(n218));
AOI22X1  g347(.A0(n390), .A1(n391), .B0(n387), .B1(v8), .Y(n392));
MX2X1    g340(.A(n184), .B(n384), .S0(n68), .Y(n385));
INVX1    g027(.A(v8), .Y(n72));
NAND2X1  g139(.A(v11), .B(n82), .Y(n184));
MX2X1    g338(.A(n63), .B(v8), .S0(n68), .Y(n383));
NOR2X1   g326(.A(n68), .B(v11), .Y(n371));
AOI21X1  g328(.A0(n372), .A1(n104), .B0(v10), .Y(n373));
INVX1    g025(.A(n69), .Y(n70_1));
NOR2X1   g279(.A(n54), .B(v12), .Y(n324));
OAI21X1  g098(.A0(n142), .A1(v0), .B0(n111), .Y(n143));
AND2X1   g019(.A(v11), .B(v12), .Y(n64));
NOR2X1   g318(.A(n72), .B(v12), .Y(n363));
NOR2X1   g038(.A(v9), .B(v10), .Y(n83));
NOR3X1   g332(.A(n47), .B(v10), .C(v1), .Y(n377));
OAI21X1  g333(.A0(n135), .A1(v3), .B0(n63), .Y(n378));
NOR2X1   g012(.A(v10), .B(v12), .Y(n57));
INVX1    g018(.A(v9), .Y(n63));
NOR2X1   g172(.A(n72), .B(v11), .Y(n217));
OAI22X1  g342(.A0(n184), .A1(n68), .B0(n45), .B1(n154), .Y(n387));
NOR2X1   g346(.A(v7), .B(n104), .Y(n391));
NAND2X1  g345(.A(n389), .B(n388), .Y(n390));
INVX1    g023(.A(v10), .Y(n68));
AOI21X1  g339(.A0(n63), .A1(v12), .B0(n104), .Y(n384));
INVX1    g037(.A(v12), .Y(n82));
INVX1    g059(.A(v11), .Y(n104));
OR2X1    g327(.A(v3), .B(v6), .Y(n372));
NOR3X1   g024(.A(n68), .B(v11), .C(v12), .Y(n69));
NAND2X1  g009(.A(v4), .B(v5), .Y(n54));
OR2X1    g066(.A(v10), .B(v11), .Y(n111));
NAND2X1  g097(.A(v10), .B(v11), .Y(n142));
NAND2X1  g002(.A(v11), .B(v12), .Y(n47));
NAND2X1  g090(.A(v10), .B(v12), .Y(n135));
NAND2X1  g109(.A(n63), .B(v12), .Y(n154));
NAND3X1  g343(.A(n90), .B(v8), .C(n82), .Y(n388));
NAND3X1  g344(.A(n68), .B(v12), .C(v1), .Y(n389));
AND2X1   g045(.A(v4), .B(v5), .Y(n90));

endmodule
