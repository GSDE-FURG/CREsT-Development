// Benchmark "c432.blif" written by ABC on Mon Jan 16 22:41:18 2017

module c432.blif ( 
    G1gat, G4gat, G8gat, G11gat, G14gat, G17gat, G21gat, G24gat, G27gat,
    G30gat, G34gat, G37gat, G40gat, G43gat, G47gat, G50gat, G53gat, G56gat,
    G60gat, G63gat, G66gat, G69gat, G73gat, G76gat, G79gat, G82gat, G86gat,
    G89gat, G92gat, G95gat, G99gat, G102gat, G105gat, G108gat, G112gat,
    G115gat,
    G223gat, G329gat, G370gat, G421gat, G430gat, G431gat, G432gat  );
  input  G1gat, G4gat, G8gat, G11gat, G14gat, G17gat, G21gat, G24gat,
    G27gat, G30gat, G34gat, G37gat, G40gat, G43gat, G47gat, G50gat, G53gat,
    G56gat, G60gat, G63gat, G66gat, G69gat, G73gat, G76gat, G79gat, G82gat,
    G86gat, G89gat, G92gat, G95gat, G99gat, G102gat, G105gat, G108gat,
    G112gat, G115gat;
  output G223gat, G329gat, G370gat, G421gat, G430gat, G431gat, G432gat;
  wire n43, n44, n45, n46, n47, n48, n49, n50, n51, n52, n53, n54, n55, n56,
    n57, n58, n59, n60, n61, n62, n63, n64, n65, n66, n67, n70, n71, n72,
    n73, n74, n76, n77, n78, n79, n80, n81, n82, n83, n84, n85, n86, n87,
    n88, n89, n90, n91, n92, n93, n94, n95, n96, n97, n98, n99, n100, n103,
    n104, n105, n109, n110, n111, n112, n113, n117, n118, n119, n123, n124,
    n125, n126, n129, n130, n131, n132, n135, n136, n137, n138, n139, n142,
    n143, n144, n145, n148, n149, n150, n151, n152, n153, n154, n158, n159,
    n160, n162, n163, n164, n165, n167, n168, n169, n170, n171, n172, n173,
    n175, n176, n177, n179, n180, n181, n182, n185, n186, n189, n190, n191,
    n194, n195, n198, n199, n200, n201, n202, n203, n204, n206, n207, n208,
    n209, n210, n213, n214, n215, n216, n217, n218, n219, n222, n223, n224,
    n225, n226, n229, n230, n231, n232, n233, n234, n237, n238, n239, n240,
    n243, n244, n245, n246, n247, n250, n251, n252, n253, n256, n257, n258,
    n259, n260, n261, n262, n264, n265, n268, n269, n272, n273, n274, n275,
    n278, n279, n282, n283, n284, n287, n288, n289, n292, n293, n294, n295,
    n298, n299, n300, n303, n304, n305, n306, n307, n308, n309, n310, n311,
    n312, n313, n314, n315, n316, n317, n318, n319, n320, n321, n322, n323,
    n324, n325, n326, n327, n328, n329, n330, n331, n332, n333, n334, n335,
    n336, n337, n338, n339, n340, n341, n342, n343, n344, n345, n346, n347,
    n348, n349, n350, n351, n352, n353, n354, n355, n356, n357, n358, n359,
    n360, n361, n362, n363, n364, n365, n366, n367, n368, n369, n370, n371,
    n372, n373, n374, n375, n376, n377, n378, n379, n381, n382, n383, n384,
    n385, n386, n387, n388, n389, n391, n392, n393, n394, n395, n396, n397,
    n398, n399, n400, n401, n402, n404, n405, n406, n407, n408, n409, n410,
    n411, n412, n413, n414, n415, n416;
  INV   g000(.A(G108gat), .Y(n43));
  NOR2  g001(.A(n43), .B(G102gat), .Y(n44));
  INV   g002(.A(G89gat), .Y(n45));
  NAND2 g003(.A(G95gat), .B(n45), .Y(n46));
  INV   g004(.A(G76gat), .Y(n47));
  NAND2 g005(.A(G82gat), .B(n47), .Y(n48));
  NAND2 g006(.A(n48), .B(n46), .Y(n49));
  NOR2  g007(.A(n49), .B(n44), .Y(n50));
  INV   g008(.A(G1gat), .Y(n51));
  NAND2 g009(.A(G4gat), .B(n51), .Y(n52));
  INV   g010(.A(G11gat), .Y(n53));
  NAND2 g011(.A(G17gat), .B(n53), .Y(n54));
  NAND2 g012(.A(n54), .B(n52), .Y(n55));
  INV   g013(.A(G43gat), .Y(n56));
  NOR2  g014(.A(n56), .B(G37gat), .Y(n57));
  INV   g015(.A(G30gat), .Y(n58));
  NOR2  g016(.A(n58), .B(G24gat), .Y(n59));
  NOR2  g017(.A(n59), .B(n57), .Y(n60));
  INV   g018(.A(G69gat), .Y(n61));
  NOR2  g019(.A(n61), .B(G63gat), .Y(n62));
  INV   g020(.A(G56gat), .Y(n63));
  NOR2  g021(.A(n63), .B(G50gat), .Y(n64));
  NOR2  g022(.A(n64), .B(n62), .Y(n65));
  NAND2 g023(.A(n65), .B(n60), .Y(n66));
  NOR2  g024(.A(n66), .B(n55), .Y(n67));
  NAND2 g025(.A(n67), .B(n50), .Y(G223gat));
  INV   g026(.A(G95gat), .Y(n70));
  NOR2  g027(.A(n70), .B(G89gat), .Y(n71));
  INV   g028(.A(G82gat), .Y(n72));
  NOR2  g029(.A(n72), .B(G76gat), .Y(n73));
  NOR2  g030(.A(n73), .B(n71), .Y(n74));
  NOR2  g031(.A(n44), .B(n99), .Y(n76));
  NOR2  g032(.A(G112gat), .B(n43), .Y(n77));
  INV   g033(.A(n77), .Y(n78));
  NOR2  g034(.A(n78), .B(n76), .Y(n79));
  INV   g035(.A(n44), .Y(n80));
  NAND2 g036(.A(n74), .B(n80), .Y(n81));
  INV   g037(.A(G4gat), .Y(n82));
  NOR2  g038(.A(n82), .B(G1gat), .Y(n83));
  INV   g039(.A(G17gat), .Y(n84));
  NOR2  g040(.A(n84), .B(G11gat), .Y(n85));
  NOR2  g041(.A(n85), .B(n83), .Y(n86));
  INV   g042(.A(G37gat), .Y(n87));
  NAND2 g043(.A(G43gat), .B(n87), .Y(n88));
  INV   g044(.A(G24gat), .Y(n89));
  NAND2 g045(.A(G30gat), .B(n89), .Y(n90));
  NAND2 g046(.A(n90), .B(n88), .Y(n91));
  INV   g047(.A(G63gat), .Y(n92));
  NAND2 g048(.A(G69gat), .B(n92), .Y(n93));
  INV   g049(.A(G50gat), .Y(n94));
  NAND2 g050(.A(G56gat), .B(n94), .Y(n95));
  NAND2 g051(.A(n95), .B(n93), .Y(n96));
  NOR2  g052(.A(n96), .B(n91), .Y(n97));
  NAND2 g053(.A(n97), .B(n86), .Y(n98));
  NOR2  g054(.A(n98), .B(n81), .Y(n99));
  NAND2 g055(.A(n99), .B(n46), .Y(n100));
  NAND2 g056(.A(n46), .B(n100), .Y(n103));
  NOR2  g057(.A(G99gat), .B(n70), .Y(n104));
  NAND2 g058(.A(n104), .B(n103), .Y(n105));
  NAND2 g059(.A(n48), .B(n100), .Y(n109));
  NOR2  g060(.A(G86gat), .B(n72), .Y(n110));
  NAND2 g061(.A(n110), .B(n109), .Y(n111));
  NAND2 g062(.A(n111), .B(n105), .Y(n112));
  NOR2  g063(.A(n112), .B(n79), .Y(n113));
  NAND2 g064(.A(n52), .B(n100), .Y(n117));
  NOR2  g065(.A(G8gat), .B(n82), .Y(n118));
  NAND2 g066(.A(n118), .B(n117), .Y(n119));
  NAND2 g067(.A(n54), .B(n100), .Y(n123));
  NOR2  g068(.A(G21gat), .B(n84), .Y(n124));
  NAND2 g069(.A(n124), .B(n123), .Y(n125));
  NAND2 g070(.A(n125), .B(n119), .Y(n126));
  NOR2  g071(.A(n57), .B(n99), .Y(n129));
  NOR2  g072(.A(G47gat), .B(n56), .Y(n130));
  INV   g073(.A(n130), .Y(n131));
  NOR2  g074(.A(n131), .B(n129), .Y(n132));
  NOR2  g075(.A(n59), .B(n99), .Y(n135));
  NOR2  g076(.A(G34gat), .B(n58), .Y(n136));
  INV   g077(.A(n136), .Y(n137));
  NOR2  g078(.A(n137), .B(n135), .Y(n138));
  NOR2  g079(.A(n138), .B(n132), .Y(n139));
  NOR2  g080(.A(n62), .B(n99), .Y(n142));
  NOR2  g081(.A(G73gat), .B(n61), .Y(n143));
  INV   g082(.A(n143), .Y(n144));
  NOR2  g083(.A(n144), .B(n142), .Y(n145));
  NOR2  g084(.A(n64), .B(n99), .Y(n148));
  NOR2  g085(.A(G60gat), .B(n63), .Y(n149));
  INV   g086(.A(n149), .Y(n150));
  NOR2  g087(.A(n150), .B(n148), .Y(n151));
  NOR2  g088(.A(n151), .B(n145), .Y(n152));
  NAND2 g089(.A(n152), .B(n139), .Y(n153));
  NOR2  g090(.A(n153), .B(n126), .Y(n154));
  NAND2 g091(.A(n154), .B(n113), .Y(G329gat));
  NOR2  g092(.A(n71), .B(n99), .Y(n158));
  INV   g093(.A(n104), .Y(n159));
  NOR2  g094(.A(n159), .B(n158), .Y(n160));
  NOR2  g095(.A(n73), .B(n99), .Y(n162));
  INV   g096(.A(n110), .Y(n163));
  NOR2  g097(.A(n163), .B(n162), .Y(n164));
  NOR2  g098(.A(n164), .B(n160), .Y(n165));
  NOR2  g099(.A(n79), .B(n203), .Y(n167));
  INV   g100(.A(n76), .Y(n168));
  NOR2  g101(.A(G115gat), .B(n43), .Y(n169));
  NAND2 g102(.A(n169), .B(n168), .Y(n170));
  NOR2  g103(.A(n170), .B(n167), .Y(n171));
  INV   g104(.A(n79), .Y(n172));
  NAND2 g105(.A(n165), .B(n172), .Y(n173));
  NOR2  g106(.A(n83), .B(n99), .Y(n175));
  INV   g107(.A(n118), .Y(n176));
  NOR2  g108(.A(n176), .B(n175), .Y(n177));
  NOR2  g109(.A(n85), .B(n99), .Y(n179));
  INV   g110(.A(n124), .Y(n180));
  NOR2  g111(.A(n180), .B(n179), .Y(n181));
  NOR2  g112(.A(n181), .B(n177), .Y(n182));
  NAND2 g113(.A(n88), .B(n100), .Y(n185));
  NAND2 g114(.A(n130), .B(n185), .Y(n186));
  NAND2 g115(.A(n90), .B(n100), .Y(n189));
  NAND2 g116(.A(n136), .B(n189), .Y(n190));
  NAND2 g117(.A(n190), .B(n186), .Y(n191));
  NAND2 g118(.A(n93), .B(n100), .Y(n194));
  NAND2 g119(.A(n143), .B(n194), .Y(n195));
  NAND2 g120(.A(n95), .B(n100), .Y(n198));
  NAND2 g121(.A(n149), .B(n198), .Y(n199));
  NAND2 g122(.A(n199), .B(n195), .Y(n200));
  NOR2  g123(.A(n200), .B(n191), .Y(n201));
  NAND2 g124(.A(n201), .B(n182), .Y(n202));
  NOR2  g125(.A(n202), .B(n173), .Y(n203));
  NAND2 g126(.A(n203), .B(n105), .Y(n204));
  NAND2 g127(.A(n105), .B(n204), .Y(n206));
  NOR2  g128(.A(G105gat), .B(n70), .Y(n207));
  NAND2 g129(.A(n207), .B(n103), .Y(n208));
  INV   g130(.A(n208), .Y(n209));
  NAND2 g131(.A(n209), .B(n206), .Y(n210));
  NAND2 g132(.A(n111), .B(n204), .Y(n213));
  NOR2  g133(.A(G92gat), .B(n72), .Y(n214));
  NAND2 g134(.A(n214), .B(n109), .Y(n215));
  INV   g135(.A(n215), .Y(n216));
  NAND2 g136(.A(n216), .B(n213), .Y(n217));
  NAND2 g137(.A(n217), .B(n210), .Y(n218));
  NOR2  g138(.A(n218), .B(n171), .Y(n219));
  NAND2 g139(.A(n119), .B(n204), .Y(n222));
  NOR2  g140(.A(G14gat), .B(n82), .Y(n223));
  NAND2 g141(.A(n223), .B(n117), .Y(n224));
  INV   g142(.A(n224), .Y(n225));
  NAND2 g143(.A(n225), .B(n222), .Y(n226));
  NAND2 g144(.A(n125), .B(n204), .Y(n229));
  NOR2  g145(.A(G27gat), .B(n84), .Y(n230));
  NAND2 g146(.A(n230), .B(n123), .Y(n231));
  INV   g147(.A(n231), .Y(n232));
  NAND2 g148(.A(n232), .B(n229), .Y(n233));
  NAND2 g149(.A(n233), .B(n226), .Y(n234));
  NOR2  g150(.A(n132), .B(n203), .Y(n237));
  NOR2  g151(.A(G53gat), .B(n56), .Y(n238));
  NAND2 g152(.A(n238), .B(n185), .Y(n239));
  NOR2  g153(.A(n239), .B(n237), .Y(n240));
  NOR2  g154(.A(n138), .B(n203), .Y(n243));
  NOR2  g155(.A(G40gat), .B(n58), .Y(n244));
  NAND2 g156(.A(n244), .B(n189), .Y(n245));
  NOR2  g157(.A(n245), .B(n243), .Y(n246));
  NOR2  g158(.A(n246), .B(n240), .Y(n247));
  NOR2  g159(.A(n145), .B(n203), .Y(n250));
  NOR2  g160(.A(G79gat), .B(n61), .Y(n251));
  NAND2 g161(.A(n251), .B(n194), .Y(n252));
  NOR2  g162(.A(n252), .B(n250), .Y(n253));
  NOR2  g163(.A(n151), .B(n203), .Y(n256));
  NOR2  g164(.A(G66gat), .B(n63), .Y(n257));
  NAND2 g165(.A(n257), .B(n198), .Y(n258));
  NOR2  g166(.A(n258), .B(n256), .Y(n259));
  NOR2  g167(.A(n259), .B(n253), .Y(n260));
  NAND2 g168(.A(n260), .B(n247), .Y(n261));
  NOR2  g169(.A(n261), .B(n234), .Y(n262));
  NAND2 g170(.A(n262), .B(n219), .Y(G370gat));
  INV   g171(.A(G14gat), .Y(n264));
  INV   g172(.A(n171), .Y(n265));
  NOR2  g173(.A(n160), .B(n203), .Y(n268));
  NOR2  g174(.A(n208), .B(n268), .Y(n269));
  NOR2  g175(.A(n164), .B(n203), .Y(n272));
  NOR2  g176(.A(n215), .B(n272), .Y(n273));
  NOR2  g177(.A(n273), .B(n269), .Y(n274));
  NAND2 g178(.A(n274), .B(n265), .Y(n275));
  NOR2  g179(.A(n177), .B(n203), .Y(n278));
  NOR2  g180(.A(n224), .B(n278), .Y(n279));
  NOR2  g181(.A(n181), .B(n203), .Y(n282));
  NOR2  g182(.A(n231), .B(n282), .Y(n283));
  NOR2  g183(.A(n283), .B(n279), .Y(n284));
  NAND2 g184(.A(n186), .B(n204), .Y(n287));
  INV   g185(.A(n239), .Y(n288));
  NAND2 g186(.A(n288), .B(n287), .Y(n289));
  NAND2 g187(.A(n190), .B(n204), .Y(n292));
  INV   g188(.A(n245), .Y(n293));
  NAND2 g189(.A(n293), .B(n292), .Y(n294));
  NAND2 g190(.A(n294), .B(n289), .Y(n295));
  NAND2 g191(.A(n195), .B(n204), .Y(n298));
  INV   g192(.A(n252), .Y(n299));
  NAND2 g193(.A(n299), .B(n298), .Y(n300));
  NAND2 g194(.A(n199), .B(n204), .Y(n303));
  INV   g195(.A(n258), .Y(n304));
  NAND2 g196(.A(n304), .B(n303), .Y(n305));
  NAND2 g197(.A(n305), .B(n300), .Y(n306));
  NOR2  g198(.A(n306), .B(n295), .Y(n307));
  NAND2 g199(.A(n307), .B(n284), .Y(n308));
  NOR2  g200(.A(n308), .B(n275), .Y(n309));
  NOR2  g201(.A(n309), .B(n264), .Y(n310));
  NAND2 g202(.A(G329gat), .B(G8gat), .Y(n311));
  NOR2  g203(.A(n99), .B(n51), .Y(n312));
  NOR2  g204(.A(n312), .B(n82), .Y(n313));
  NAND2 g205(.A(n313), .B(n311), .Y(n314));
  NOR2  g206(.A(n314), .B(n310), .Y(n315));
  INV   g207(.A(G66gat), .Y(n316));
  NOR2  g208(.A(n309), .B(n316), .Y(n317));
  NAND2 g209(.A(G329gat), .B(G60gat), .Y(n318));
  NOR2  g210(.A(n99), .B(n94), .Y(n319));
  NOR2  g211(.A(n319), .B(n63), .Y(n320));
  NAND2 g212(.A(n320), .B(n318), .Y(n321));
  NOR2  g213(.A(n321), .B(n317), .Y(n322));
  INV   g214(.A(G53gat), .Y(n323));
  NOR2  g215(.A(n309), .B(n323), .Y(n324));
  NAND2 g216(.A(G329gat), .B(G47gat), .Y(n325));
  NOR2  g217(.A(n99), .B(n87), .Y(n326));
  NOR2  g218(.A(n326), .B(n56), .Y(n327));
  NAND2 g219(.A(n327), .B(n325), .Y(n328));
  NOR2  g220(.A(n328), .B(n324), .Y(n329));
  NOR2  g221(.A(n329), .B(n322), .Y(n330));
  INV   g222(.A(G92gat), .Y(n331));
  NOR2  g223(.A(n309), .B(n331), .Y(n332));
  NAND2 g224(.A(G329gat), .B(G86gat), .Y(n333));
  NOR2  g225(.A(n99), .B(n47), .Y(n334));
  NOR2  g226(.A(n334), .B(n72), .Y(n335));
  NAND2 g227(.A(n335), .B(n333), .Y(n336));
  NOR2  g228(.A(n336), .B(n332), .Y(n337));
  INV   g229(.A(G79gat), .Y(n338));
  NOR2  g230(.A(n309), .B(n338), .Y(n339));
  NAND2 g231(.A(G329gat), .B(G73gat), .Y(n340));
  NOR2  g232(.A(n99), .B(n92), .Y(n341));
  NOR2  g233(.A(n341), .B(n61), .Y(n342));
  NAND2 g234(.A(n342), .B(n340), .Y(n343));
  NOR2  g235(.A(n343), .B(n339), .Y(n344));
  NOR2  g236(.A(n344), .B(n337), .Y(n345));
  NAND2 g237(.A(n345), .B(n330), .Y(n346));
  INV   g238(.A(G27gat), .Y(n347));
  NOR2  g239(.A(n309), .B(n347), .Y(n348));
  NAND2 g240(.A(G329gat), .B(G21gat), .Y(n349));
  NOR2  g241(.A(n99), .B(n53), .Y(n350));
  NOR2  g242(.A(n350), .B(n84), .Y(n351));
  NAND2 g243(.A(n351), .B(n349), .Y(n352));
  NOR2  g244(.A(n352), .B(n348), .Y(n353));
  INV   g245(.A(G40gat), .Y(n354));
  NOR2  g246(.A(n309), .B(n354), .Y(n355));
  NAND2 g247(.A(G329gat), .B(G34gat), .Y(n356));
  NOR2  g248(.A(n99), .B(n89), .Y(n357));
  NOR2  g249(.A(n357), .B(n58), .Y(n358));
  NAND2 g250(.A(n358), .B(n356), .Y(n359));
  NOR2  g251(.A(n359), .B(n355), .Y(n360));
  NOR2  g252(.A(n360), .B(n353), .Y(n361));
  INV   g253(.A(G115gat), .Y(n362));
  NOR2  g254(.A(n309), .B(n362), .Y(n363));
  NAND2 g255(.A(G329gat), .B(G112gat), .Y(n364));
  INV   g256(.A(G102gat), .Y(n365));
  NOR2  g257(.A(n99), .B(n365), .Y(n366));
  NOR2  g258(.A(n366), .B(n43), .Y(n367));
  NAND2 g259(.A(n367), .B(n364), .Y(n368));
  NOR2  g260(.A(n368), .B(n363), .Y(n369));
  INV   g261(.A(G105gat), .Y(n370));
  NOR2  g262(.A(n309), .B(n370), .Y(n371));
  NAND2 g263(.A(G329gat), .B(G99gat), .Y(n372));
  NOR2  g264(.A(n99), .B(n45), .Y(n373));
  NOR2  g265(.A(n373), .B(n70), .Y(n374));
  NAND2 g266(.A(n374), .B(n372), .Y(n375));
  NOR2  g267(.A(n375), .B(n371), .Y(n376));
  NOR2  g268(.A(n376), .B(n369), .Y(n377));
  NAND2 g269(.A(n377), .B(n361), .Y(n378));
  NOR2  g270(.A(n378), .B(n346), .Y(n379));
  NOR2  g271(.A(n379), .B(n315), .Y(G421gat));
  NAND2 g272(.A(G370gat), .B(G40gat), .Y(n381));
  INV   g273(.A(n359), .Y(n382));
  NAND2 g274(.A(n382), .B(n381), .Y(n383));
  NAND2 g275(.A(n383), .B(n329), .Y(n384));
  NAND2 g276(.A(G370gat), .B(G27gat), .Y(n385));
  INV   g277(.A(n352), .Y(n386));
  NAND2 g278(.A(n386), .B(n385), .Y(n387));
  NAND2 g279(.A(n383), .B(n387), .Y(n388));
  NOR2  g280(.A(n388), .B(n322), .Y(n389));
  NAND2 g281(.A(n389), .B(n384), .Y(G430gat));
  NOR2  g282(.A(n360), .B(n329), .Y(n391));
  NAND2 g283(.A(G370gat), .B(G79gat), .Y(n392));
  INV   g284(.A(n343), .Y(n393));
  NAND2 g285(.A(n393), .B(n392), .Y(n394));
  NOR2  g286(.A(n394), .B(n322), .Y(n395));
  NAND2 g287(.A(n395), .B(n391), .Y(n396));
  NAND2 g288(.A(G370gat), .B(G66gat), .Y(n397));
  INV   g289(.A(n321), .Y(n398));
  NAND2 g290(.A(n398), .B(n397), .Y(n399));
  NAND2 g291(.A(n337), .B(n399), .Y(n400));
  NOR2  g292(.A(n400), .B(n329), .Y(n401));
  NOR2  g293(.A(n401), .B(n388), .Y(n402));
  NAND2 g294(.A(n402), .B(n396), .Y(G431gat));
  NAND2 g295(.A(G370gat), .B(G105gat), .Y(n404));
  INV   g296(.A(n375), .Y(n405));
  NAND2 g297(.A(n405), .B(n404), .Y(n406));
  NOR2  g298(.A(n406), .B(n337), .Y(n407));
  NAND2 g299(.A(n407), .B(n391), .Y(n408));
  NAND2 g300(.A(G370gat), .B(G53gat), .Y(n409));
  INV   g301(.A(n328), .Y(n410));
  NAND2 g302(.A(n410), .B(n409), .Y(n411));
  NAND2 g303(.A(n383), .B(n411), .Y(n412));
  NAND2 g304(.A(n344), .B(n399), .Y(n413));
  NOR2  g305(.A(n413), .B(n412), .Y(n414));
  NAND2 g306(.A(n384), .B(n387), .Y(n415));
  NOR2  g307(.A(n415), .B(n414), .Y(n416));
  NAND2 g308(.A(n416), .B(n408), .Y(G432gat));
endmodule


