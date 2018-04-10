/**
 * Created by gperezv on 28-03-18.
 */
'use strict';

const React = require('react');
const ReactDOM = require('react-dom')


class AppAccount extends React.Component{
    render() {
        return (
            <div className="col-lg-12">
                <h1>Página principal del contador módulos Gemesys</h1>
            </div>
        );
    }
}

ReactDOM.render(<AppAccount />,document.getElementById('react')
)
