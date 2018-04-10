/**
 * Created by gperezv on 09-04-18.
 */

'use strict';

const React = require('react');
const ReactDOM = require('react-dom')


class AppHome extends React.Component{
    render() {
        return (
            <div className="col-lg-12">
                <h1>Página de inicio (Home)</h1>
            </div>
        );
    }
}

ReactDOM.render(<AppHome />,document.getElementById('react')
)
