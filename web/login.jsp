<%-- 
    Document   : index
    Created on : Mar 31, 2014, 9:11:40 PM
    Author     : daradermody
--%>

<%@page import="mainPackage.Security"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="description" content="Website for forum application for Distributed Systems Project II">
        <meta name="keywords" content="distributed systems project forum application java servlet security">

        <link rel="stylesheet" type="text/css" href="style.css" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.js"></script>
        <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha1.js"></script>
        <script type="text/javascript">
            function sha1(str) {
                //   example 1: sha1('Kevin van Zonneveld');
                //   returns 1: '54916d2e62f65b3afa6e192e6a601cdbe5cb5897'
                
                var rotate_left = function(n, s) {
                    var t4 = (n << s) | (n >>> (32 - s));
                    return t4;
                };
                var cvt_hex = function(val) {
                    var str = '';
                    var i;
                    var v;

                    for (i = 7; i >= 0; i--) {
                        v = (val >>> (i * 4)) & 0x0f;
                        str += v.toString(16);
                    }
                    return str;
                };

                var blockstart;
                var i, j;
                var W = new Array(80);
                var H0 = 0x67452301;
                var H1 = 0xEFCDAB89;
                var H2 = 0x98BADCFE;
                var H3 = 0x10325476;
                var H4 = 0xC3D2E1F0;
                var A, B, C, D, E;
                var temp;

                str = this.utf8_encode(str);
                var str_len = str.length;

                var word_array = [];
                for (i = 0; i < str_len - 3; i += 4) {
                    j = str.charCodeAt(i) << 24 | str.charCodeAt(i + 1) << 16 | str.charCodeAt(i + 2) << 8 | str.charCodeAt(i + 3);
                    word_array.push(j);
                }

                switch (str_len % 4) {
                    case 0:
                        i = 0x080000000;
                        break;
                    case 1:
                        i = str.charCodeAt(str_len - 1) << 24 | 0x0800000;
                        break;
                    case 2:
                        i = str.charCodeAt(str_len - 2) << 24 | str.charCodeAt(str_len - 1) << 16 | 0x08000;
                        break;
                    case 3:
                        i = str.charCodeAt(str_len - 3) << 24 | str.charCodeAt(str_len - 2) << 16 | str.charCodeAt(str_len - 1) <<
                                8 | 0x80;
                        break;
                }

                word_array.push(i);

                while ((word_array.length % 16) !== 14) {
                    word_array.push(0);
                }

                word_array.push(str_len >>> 29);
                word_array.push((str_len << 3) & 0x0ffffffff);

                for (blockstart = 0; blockstart < word_array.length; blockstart += 16) {
                    for (i = 0; i < 16; i++) {
                        W[i] = word_array[blockstart + i];
                    }
                    for (i = 16; i <= 79; i++) {
                        W[i] = rotate_left(W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16], 1);
                    }

                    A = H0;
                    B = H1;
                    C = H2;
                    D = H3;
                    E = H4;

                    for (i = 0; i <= 19; i++) {
                        temp = (rotate_left(A, 5) + ((B & C) | (~B & D)) + E + W[i] + 0x5A827999) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    for (i = 20; i <= 39; i++) {
                        temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    for (i = 40; i <= 59; i++) {
                        temp = (rotate_left(A, 5) + ((B & C) | (B & D) | (C & D)) + E + W[i] + 0x8F1BBCDC) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    for (i = 60; i <= 79; i++) {
                        temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & 0x0ffffffff;
                        E = D;
                        D = C;
                        C = rotate_left(B, 30);
                        B = A;
                        A = temp;
                    }

                    H0 = (H0 + A) & 0x0ffffffff;
                    H1 = (H1 + B) & 0x0ffffffff;
                    H2 = (H2 + C) & 0x0ffffffff;
                    H3 = (H3 + D) & 0x0ffffffff;
                    H4 = (H4 + E) & 0x0ffffffff;
                }

                temp = cvt_hex(H0) + cvt_hex(H1) + cvt_hex(H2) + cvt_hex(H3) + cvt_hex(H4);
                return temp.toLowerCase();
            }
            function hello() {
                return "testing test function"
            }
            $(function() {
                $('#login-fields').submit(function() {
                    alert("submit function running");
                    $passwd = $('input[name="password"]');
                    $passwd.val(CryptoJS.SHA1($passwd.val()));
                    alert("submit function finished");
                    return true;
                });
            });

//                $('#login-fields').on('submit', function(e) {
//                    alert("test");
//                    $passwd = $('input[name="password"]');
//                    alert(sha1("this is a password"));
//                    $passwd.val(test("this is a password"));
//                    alert($passwd.val());
//                    $(this).submit();
//                    e.preventDefault();
//                });
//            });
        </script>
    </head>
    <body>
        <%
            // Add session ID cookie to test if cookies enabled (tested in index.jsp)
            response.addCookie(new Cookie("id", ""));

            // Get address of intended location (before forward to login page)
            String address = Security.sanitise((String) request.getAttribute("address"));
            if (address.equals("")) {
                address = "index.jsp";
            }

            // If user submitted parameters for inteded page, retrieve them and add them (except username/passwords)
            if (!request.getParameterMap().isEmpty()) {
                address += "?";
                for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                    if (!entry.getKey().equals("username") && !entry.getKey().equals("password")) {
                        address += String.format("%s=%s&", Security.sanitise(entry.getKey()), Security.sanitise(entry.getValue()[0]));
                    }
                }
                address = address.substring(0, address.length() - 1); // Truncate last & character
            }

        %>

        <div class="main-body">
            <header>
                <div id="logo">Distributed Systems Project II</div>
            </header>
            <div class="big-wrapper" id="login-container">
                <div id="login-image">
                </div>
                <form id="login-fields" name="login" action="<%= address%>" method="POST">
                    <ol>
                        <li><input type="text" class="login-text-field" placeholder="Username" name="username"></li>
                        <li><input type="password" class="login-text-field" placeholder="Password" name="password"></li>
                        <li>
                            <input type="submit" value="Login"> 
                            <%
                                if (request.getAttribute("invalid-login") != "") {
                                    out.println("<span class='error-message'>Invalid login! Bears "
                                            + "are now flocking to your location.</span>");
                                }
                            %>
                        </li>
                    </ol>
                </form>
            </div>
        </div>
    </body>
</html>