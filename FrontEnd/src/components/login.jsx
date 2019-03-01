import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Nav from 'react-bootstrap/Nav'
import NavBar from 'react-bootstrap/Navbar';
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
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
                    {/*<li className="nav-item">*/}
                        {/*<button type="button" className="btn btn-outline-danger m-1" style={{color: 'red', borderColor: 'red'}}>Login</button>*/}
                    {/*</li>*/}
                    <li className="nav-item">
                        <button type="button" className="btn btn-danger m-1 float-right" style={{borderColor: 'red', backgroundColor:'red'}}>Sign Up</button>
                    </li>
                </ul>
            </NavBar>
            <div style={{display: 'flex', justifyContent: 'center'}} className="pt-5">
                <Form>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" />
                        <Form.Text className="text-muted">
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" />
                    </Form.Group>
                    <Form.Group controlId="formBasicChecbox">
                        <Form.Check type="checkbox" label="Remember me" />
                    </Form.Group>
                    <button type="button" className="btn btn-outline-danger" style={{color: 'red', borderColor: 'red'}}>Log In</button>
                </Form>
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