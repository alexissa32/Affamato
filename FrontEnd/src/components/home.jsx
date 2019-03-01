import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Nav from 'react-bootstrap/Nav'
import NavBar from 'react-bootstrap/Navbar';
import Button from 'react-bootstrap/Button'
import Jumbotron from 'react-bootstrap/Jumbotron'
class Home extends Component {
    state = {
        imageURL: 'https://i.imgur.com/YDa25F4.png',
        fullLogo: 'https://i.imgur.com/ZJ9MNoJ.png'
    };

    styles = {
        color: "red",
        fontSize: '15px',
    };

    align = {
        align: "right"
    };

    render() {
        return <React.Fragment>
            <NavBar bg="white" expand="lg" >
                <img src={this.state.imageURL} alt="" className=""/>
                <NavBar.Toggle aria-controls="basic-navbar-nav" />
                <ul className="nav navbar-nav ml-auto w-100">

                    <li className="nav-item">
                        <Nav.Link href="#home"><div className="m-1 pl-3" style={this.styles}>How It Works</div></Nav.Link>
                    </li>
                    <li className="nav-item">
                        <Nav.Link href="#link"><div className="m-1"  style={this.styles}>Jobs </div></Nav.Link>
                    </li>
                    <li className="nav-item">
                        <Nav.Link href="#link"><div className="m-1"  style={this.styles}>Who We Are</div></Nav.Link>
                    </li>
                </ul>
                <ul className="nav navbar-nav ml-auto w-100 justify-content-end">
                    <li className="nav-item"><button type="button" className="btn btn-outline-danger m-1" style={{color: 'red', borderColor: 'red'}}>Login</button>
                    </li>
                    <li className="nav-item">
                        <button type="button" className="btn btn-danger m-1 float-right" style={{borderColor: 'red', backgroundColor:'red'}}>Sign Up</button>
                    </li>
                </ul>
            </NavBar>
            <div style={{display: 'flex', justifyContent: 'flex-begin'}} className="pl-3">
            <Jumbotron style={{width: 800}}>
                <h1>Manage your pantry</h1>
                <h1>Discover new Recipes</h1>
                <h1>Save money</h1>
                {/*<p>*/}
                    {/*This is a simple hero unit, a simple jumbotron-style component for calling*/}
                    {/*extra attention to featured content or information.*/}
                {/*</p>*/}
                <p className="pt-3">
                    <button type="button" className="btn btn-danger" style={{borderColor: 'red', backgroundColor:'red'}}>Learn More</button>
                </p>
            </Jumbotron>
                <img src={this.state.fullLogo} alt="" className=""/>
            </div>
        </React.Fragment>
    }
}

//for searching:
{/*<Form inline>*/}
    {/*<FormControl type="text" placeholder="Search" className="mr-sm-2" />*/}
    {/*<Button variant="outline-success">Search</Button>*/}
{/*</Form>*/}

export default Home;