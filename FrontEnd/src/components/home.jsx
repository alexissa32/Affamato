import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Nav from 'react-bootstrap/Nav'
import NavBar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown'
import Form from 'react-bootstrap/Form'
import FormControl from 'react-bootstrap/FormControl'
import Button from 'react-bootstrap/Button'

class Home extends Component {
    state = {
        imageURL: 'https://i.imgur.com/YDa25F4.png'
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
                {/*<ul class="nav navbar-nav ml-auto w-100 justify-content-end">*/}
                {/*<NavBar.Collapse id="basic-navbar-nav">*/}
                    {/*<Nav className="mr-auto">*/}
                        {/*<Nav.Link href="#home"><div className="m-1" style={this.styles}>How</div></Nav.Link>*/}
                        {/*<Nav.Link href="#link"><div className="m-1"  style={this.styles}>Jobs </div></Nav.Link>*/}
                        {/*<Nav.Link href="#link"><div className="m-1"  style={this.styles}>Who We Are</div></Nav.Link>*/}
                        {/*<button type="button" className="btn btn-outline-danger m-1" style={{color: 'red', borderColor: 'red'}}>Login</button>*/}
                        {/*<button type="button" className="btn btn-danger m-1 float-right" style={{borderColor: 'red', backgroundColor:'red'}}>Sign Up</button>*/}
                    {/*</Nav>*/}
                {/*</NavBar.Collapse>*/}
                {/*</ul>*/}
                <ul className="nav navbar-nav ml-auto w-100">

                    <li className="nav-item">
                        <Nav.Link href="#home"><div className="m-1" style={this.styles}>How It Works</div></Nav.Link>
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
        </React.Fragment>
    }
}

//for searching:
{/*<Form inline>*/}
    {/*<FormControl type="text" placeholder="Search" className="mr-sm-2" />*/}
    {/*<Button variant="outline-success">Search</Button>*/}
{/*</Form>*/}

export default Home;