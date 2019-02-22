import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class Dashboard extends Component {
    render() {

        return <div>
            <h1>Welcome -user- </h1>
            <br></br>
            <input type="text" placeholder="Search"></input>
                {/*<input type="text" placeholder="Search.." id="myInput" onKeyUp="filterFunction()">*/}
                    {/*<a href="#about">About</a>*/}
                    {/*<a href="#base">Base</a>*/}
                    {/*<a href="#blog">Blog</a>*/}
                    {/*<a href="#contact">Contact</a>*/}
                    {/*<a href="#custom">Custom</a>*/}
                    {/*<a href="#support">Support</a>*/}
                    {/*<a href="#tools">Tools</a>*/}
                {/*</input>*/}
            <br></br>
            <br></br>
            <button type="button" className="btn btn-success">Discover</button>
            <button type="button" class="btn btn-outline-info">Inventory</button>
            <button type="button" className="btn btn-outline-info">My Recipes</button>
            <button type="button" className="btn btn-outline-info">My Grocery Lists</button>
            <br></br>
            <br></br>
            <button type="button" className="btn btn-outline-info">Settings</button>
            <button type="button" className="btn btn-danger">Logout</button>
        </div>;
    }
}

export default Dashboard;