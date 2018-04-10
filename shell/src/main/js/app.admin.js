'use strict';

const React = require('react');
const ReactDOM = require('react-dom')


class AppAdmin extends React.Component{
    render() {
        return (
            <div className="col-lg-12">
                <h1>Página principal del administrador módulos Gemesys</h1>
            </div>
        );
    }
}

ReactDOM.render(<AppAdmin />,document.getElementById('react')
)
