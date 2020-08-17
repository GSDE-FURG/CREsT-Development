//Converted to Combinational , Module name: s444 , Timestamp: 2018-12-03T15:51:01.735259 
module s444 ( G0, G1, G2, G11, G12, G13, G14, G15, G16, G17, G18, G19, G20, G21, G22, G23, G24, G25, G26, G27, G28, G29, G30, G31, G118, G167, G107, G119, G168, G108, n19, n24, n29, n34, n39, n44, n49, n54, n59, n64, n69, n74, n79, n84, n89, n94, n99, n104, n109, n114, n119 );
input G0, G1, G2, G11, G12, G13, G14, G15, G16, G17, G18, G19, G20, G21, G22, G23, G24, G25, G26, G27, G28, G29, G30, G31;
output G118, G167, G107, G119, G168, G108, n19, n24, n29, n34, n39, n44, n49, n54, n59, n64, n69, n74, n79, n84, n89, n94, n99, n104, n109, n114, n119;
wire n74_1, n75, n76, n78, n79_1, n81, n82, n84_1, n85, n87, n88, n89_1, n90, n91, n92, n93, n94_1, n95, n96, n98, n99_1, n101, n102, n104_1, n105, n107, n108, n109_1, n110, n112, n113, n115, n116, n117, n119_1, n120, n121, n122, n123, n125, n126, n128, n129, n130, n131, n132, n133, n134, n135, n137, n138, n139, n140, n142, n143, n144, n145, n147, n148, n150, n152, n153, n154, n155, n158, n159;
INVX1    g00(.A(G29), .Y(G167));
INVX1    g01(.A(G28), .Y(G119));
INVX1    g02(.A(G14), .Y(n74_1));
NOR3X1   g03(.A(G13), .B(G12), .C(G11), .Y(n75));
NOR2X1   g04(.A(n75), .B(n74_1), .Y(n76));
NOR3X1   g05(.A(n76), .B(G11), .C(G0), .Y(n19));
INVX1    g06(.A(G11), .Y(n78));
XOR2X1   g07(.A(G12), .B(n78), .Y(n79_1));
NOR3X1   g08(.A(n79_1), .B(n76), .C(G0), .Y(n24));
NAND2X1  g09(.A(G12), .B(G11), .Y(n81));
XOR2X1   g10(.A(n81), .B(G13), .Y(n82));
NOR3X1   g11(.A(n82), .B(n76), .C(G0), .Y(n29));
NAND3X1  g12(.A(G13), .B(G12), .C(G11), .Y(n84_1));
XOR2X1   g13(.A(n84_1), .B(G14), .Y(n85));
NOR3X1   g14(.A(n85), .B(n76), .C(G0), .Y(n34));
INVX1    g15(.A(G15), .Y(n87));
INVX1    g16(.A(G31), .Y(n88));
OAI21X1  g17(.A0(n75), .A1(n74_1), .B0(n88), .Y(n89_1));
XOR2X1   g18(.A(n89_1), .B(n87), .Y(n90));
INVX1    g19(.A(G0), .Y(n91));
INVX1    g20(.A(G16), .Y(n92));
INVX1    g21(.A(G17), .Y(n93));
NAND3X1  g22(.A(n93), .B(n92), .C(n87), .Y(n94_1));
NAND3X1  g23(.A(n94_1), .B(n89_1), .C(G18), .Y(n95));
NAND2X1  g24(.A(n95), .B(n91), .Y(n96));
NOR2X1   g25(.A(n96), .B(n90), .Y(n39));
AND2X1   g26(.A(n89_1), .B(G15), .Y(n98));
XOR2X1   g27(.A(n98), .B(n92), .Y(n99_1));
NOR2X1   g28(.A(n99_1), .B(n96), .Y(n44));
NAND3X1  g29(.A(n89_1), .B(G16), .C(G15), .Y(n101));
XOR2X1   g30(.A(n101), .B(G17), .Y(n102));
NOR2X1   g31(.A(n102), .B(n96), .Y(n49));
NAND4X1  g32(.A(G17), .B(G16), .C(G15), .D(n89_1), .Y(n104_1));
XOR2X1   g33(.A(n104_1), .B(G18), .Y(n105));
NOR2X1   g34(.A(n105), .B(n96), .Y(n54));
XOR2X1   g35(.A(n95), .B(G19), .Y(n107));
OR2X1    g36(.A(G20), .B(G19), .Y(n108));
OAI21X1  g37(.A0(n108), .A1(G21), .B0(G22), .Y(n109_1));
OAI21X1  g38(.A0(n109_1), .A1(n95), .B0(n91), .Y(n110));
NOR2X1   g39(.A(n110), .B(n107), .Y(n59));
NAND4X1  g40(.A(n89_1), .B(G19), .C(G18), .D(n94_1), .Y(n112));
XOR2X1   g41(.A(n112), .B(G20), .Y(n113));
NOR2X1   g42(.A(n113), .B(n110), .Y(n64));
AND2X1   g43(.A(G20), .B(G19), .Y(n115));
NAND4X1  g44(.A(n94_1), .B(n89_1), .C(G18), .D(n115), .Y(n116));
XOR2X1   g45(.A(n116), .B(G21), .Y(n117));
NOR2X1   g46(.A(n117), .B(n110), .Y(n69));
INVX1    g47(.A(G21), .Y(n119_1));
NAND2X1  g48(.A(G20), .B(G19), .Y(n120));
NOR2X1   g49(.A(n120), .B(n119_1), .Y(n121));
NAND4X1  g50(.A(n94_1), .B(n89_1), .C(G18), .D(n121), .Y(n122));
XOR2X1   g51(.A(n122), .B(G22), .Y(n123));
NOR2X1   g52(.A(n123), .B(n110), .Y(n74));
OR2X1    g53(.A(G23), .B(G2), .Y(n125));
AOI21X1  g54(.A0(G23), .A1(G2), .B0(G0), .Y(n126));
AND2X1   g55(.A(n126), .B(n125), .Y(n79));
INVX1    g56(.A(G23), .Y(n128));
NOR4X1   g57(.A(n128), .B(n119_1), .C(G0), .D(n108), .Y(n129));
INVX1    g58(.A(G20), .Y(n130));
NOR2X1   g59(.A(G23), .B(G22), .Y(n131));
NAND4X1  g60(.A(G21), .B(n130), .C(G19), .D(n131), .Y(n132));
INVX1    g61(.A(G24), .Y(n133));
NOR2X1   g62(.A(n133), .B(G0), .Y(n134));
AOI21X1  g63(.A0(n134), .A1(n132), .B0(n129), .Y(n135));
INVX1    g64(.A(n135), .Y(n84));
NAND3X1  g65(.A(n131), .B(n130), .C(G19), .Y(n137));
AND2X1   g66(.A(G23), .B(G22), .Y(n138));
NOR3X1   g67(.A(n138), .B(n119_1), .C(G0), .Y(n139));
OAI21X1  g68(.A0(n108), .A1(n128), .B0(n139), .Y(n140));
AOI21X1  g69(.A0(n137), .A1(G24), .B0(n140), .Y(n89));
INVX1    g70(.A(G22), .Y(n142));
NOR3X1   g71(.A(n142), .B(G19), .C(G0), .Y(n143));
NOR2X1   g72(.A(n120), .B(G0), .Y(n144));
AOI21X1  g73(.A0(n133), .A1(n119_1), .B0(G0), .Y(n145));
NOR3X1   g74(.A(n145), .B(n144), .C(n143), .Y(n94));
NAND4X1  g75(.A(n119_1), .B(n130), .C(G19), .D(n133), .Y(n147));
NAND3X1  g76(.A(n133), .B(n142), .C(n119_1), .Y(n148));
NAND3X1  g77(.A(n148), .B(n147), .C(n91), .Y(n99));
NOR3X1   g78(.A(n108), .B(n142), .C(G0), .Y(n150));
OAI22X1  g79(.A0(n145), .A1(n150), .B0(n135), .B1(n93), .Y(n104));
AND2X1   g80(.A(n134), .B(n132), .Y(n152));
NAND4X1  g81(.A(n142), .B(G21), .C(n91), .D(G23), .Y(n153));
NAND3X1  g82(.A(G22), .B(n119_1), .C(n91), .Y(n154));
AOI21X1  g83(.A0(n154), .A1(n153), .B0(n108), .Y(n155));
OAI22X1  g84(.A0(n135), .A1(n93), .B0(n152), .B1(n155), .Y(n109));
NOR4X1   g85(.A(G24), .B(G21), .C(G0), .D(n120), .Y(n114));
OR2X1    g86(.A(G31), .B(G1), .Y(n158));
AOI21X1  g87(.A0(G31), .A1(G1), .B0(G0), .Y(n159));
AND2X1   g88(.A(n159), .B(n158), .Y(n119));
BUFX1    g89(.A(G27), .Y(G118));
BUFX1    g90(.A(G25), .Y(G107));
BUFX1    g91(.A(G30), .Y(G168));
BUFX1    g92(.A(G26), .Y(G108));
endmodule