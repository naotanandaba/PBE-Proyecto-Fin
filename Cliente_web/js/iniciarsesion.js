/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    //variable que guarda el identificador de la app en facebook developers
    var app_id = '226068234399569';
    //los permisos que utilizara nuestra app
    var scopes = 'email, user_friends';// da error cuando habilitas este scope user_online_presence  PREGUNTAR PORQUE

    //necesario crear esta variable porque cada vez que cierras sesion necesitas volver a iniciarla
    var btn_login = '<input type="submit" id="login" value="Iniciar Sesión con Facebook" class="btn btn-primary btn-lg" />';

    /* var div_session = "<div id='fcb_session'>" +
     "<strong> </strong>" +
     "<img>" +
     '<input type="submit" id="logout" value="Cerrar Sesión" />' +
     "</div>";
     */

    window.fbAsyncInit = function () {
        FB.init({
            appId: app_id,
            status: true,
            cookie: true,
            xfbml: true,
            version: 'v2.2'
        });

        //obetener el login status
        FB.getLoginStatus(function (response) {
            statusChangeCallback(response, function () {
            });
        });
    };

    var statusChangeCallback = function (response, callback) {
        console.log(response);

        if (response.status === 'connected') {
            getFacebookData();
        } else {
            callback(false);
        }
    }

    var checkLoginState = function (callback) {
        FB.getLoginStatus(function (response) {
            callback(response);
        });
    }

    var getFacebookData = function ()
    {
        FB.api('\me', function (response) {
            $('#login').after(div_session);
            $('#login').remove();
            $('#fcb_session strong').text("Bienvenido: " + response.name);
            $('#fcb_session img').attr('src', 'http://graph.facebook.com/' + response.id + '/picture?type=large');

        })
    }

    var facebookLogin = function () {
        checkLoginState(function (data) {
            if (data.status !== 'connected') {
                FB.login(function (response) {
                    if (response.status === 'connected')
                        getFacebookData();
                        location.href = "http://localhost/PlanealoProject/home.php";
                }, {scope: scopes});
            }
        })
    }

    var facebookLogout = function () {
        checkLoginState(function (data)
        {
            if (data.status === 'connected') {
                FB.logout(function (response) {
                    //$('#fcb_session').before(btn_login);
                    //$('#fcb_session').remove();
                    location.href = "http://localhost/PlanealoProject/index.php";
                })
            }
        })
    }

    $(document).on('click', '#login', function (e) {
        e.preventDefault();

        facebookLogin();
    })

    $(document).on('click', '#logout', function (e) {
        e.preventDefault();

        if (confirm("¿Quiere cerrar la sesión?")) {
            facebookLogout();
        } else
            return false;
    })

})

