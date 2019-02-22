import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class Home extends Component {
    render() {

        return <div>
            <h1>Affomato</h1>
            <br></br>
            <input type="text" placeholder="Enter username"></input>
            <br></br>
            <br></br>
            <input type="text" placeholder="Enter password"></input>
            <br></br>
            <br></br>
            <button type="button" class="btn btn-outline-info">Login</button>
            <button type="button" class="btn btn-success">Sign Up</button>
        </div>;
    }
}

export default Home;