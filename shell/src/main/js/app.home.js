/**
 * Created by gperezv on 09-04-18.
 */

'use strict';

const React = require('react');
const ReactDOM = require('react-dom')


class AppHome extends React.Component {
    render() {
        return (
            <div>
                <AppHomeHeader/>
                <ModuleBox/>
            </div>
        )
    }
}

class AppHomeHeader extends React.Component {
    render() {
        return (
            <nav className="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
                <div>
                    <div className="navbar-header">
                        <a className="navbar-brand" href="#">GEMESYS Administración</a>
                    </div>

                    <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul className="nav navbar-nav">
                            <li className="active">
                                <a href="home.html">
                                    <span className="glyphicon glyphicon-home"></span>
                                    Home
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <span className="glyphicon glyphicon-king"></span>
                                    Acerca de...
                                </a>
                            </li>
                        </ul>

                        <ul className="nav navbar-nav navbar-right">

                            <li>
                                <a href="#">
                                    <span className="glyphicon glyphicon-user"></span> Perfil del Usuario
                                </a>
                            </li>

                            <li>
                                <a href="/logout">
                                    <span className="glyphicon glyphicon-log-out"></span> Cerrar Sesión
                                </a>
                            </li>

                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}


class ModuleBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    getData() {
        $.ajax({
            url: '/rest/modulos/',
            method: 'get',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                this.setState({data: response});
            }.bind(this),
            error: function (err) {
                location.href = '';
            }.bind(this),
            beforeSend: function () {
                $('#preloader').show();
            }.bind(this),
            complete: function () {
                $('#preloader').hide();
            }.bind(this)
        });
    }

    getMenusByModulo(listmenus) {

        if (listmenus.length > 0) {
            let menus = listmenus.map((menuData, index) => {
                return (
                    <li key={menuData.id}>
                        <a href="google.cl">{menuData.name}</a>
                    </li>
                );
            })
            return menus;
        }
        else {
            return null;
        }
    }

    componentDidMount() {
        this.getData();
    }


    render() {

        var self = this;

        let modulos = this.state.data.map(function (moduloData, index) {

            var listmenus = self.getMenusByModulo(moduloData.menus);

            return (
                <div key={moduloData.id} className="col-sm-4">
                    <div className="panel panel-primary">
                        <div className="panel-heading">
                            <h6 className="panel-title">{moduloData.name}</h6>
                        </div>
                        <div className="panel-body">
                            <ul>
                                {listmenus}
                            </ul>
                        </div>
                    </div>
                </div>


            );
        })


        return (

            <div className="container-fluid">
                <div className="row">
                        <h5 className="bg-dark text-white">MÓDULOS DISPONIBLES</h5>
                </div>

                <div className="row">
                    {modulos}
                </div>
            </div>


        )
    }
}


ReactDOM.render(<AppHome/>, document.getElementById('react'))
