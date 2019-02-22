import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import Home from './components/home';
import Dashboard from './components/dashboard'
import 'bootstrap/dist/css/bootstrap.css';

ReactDOM.render(<Dashboard />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
// serviceWorker.unregister();

    // const element = <h1>Hello World</h1>;
    // console.log(element);
    // ReactDOM.render(element, document.getElementById('root'));