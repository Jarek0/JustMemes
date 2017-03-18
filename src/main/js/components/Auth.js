import React from 'react';
import {Nav,NavItem,Panel} from 'react-bootstrap';
import Login from './Login';
import Register from './Register';
import {Row,Col} from 'react-bootstrap';

export default class AuthPanel extends React.Component {
    constructor(props){
        super(props);
        this.state = {login:true,activeKey: this.props.location.pathname};
    }

    getInitialState() {
        return {activeKey: this.props.location.pathname};
    }

    handleSelect(eventKey) {
        this.setState({activeKey: eventKey});
        if (eventKey=="/login")
            this.setState({login:true});
        else if(eventKey=="/register")
            this.setState({login:false});
    }


    render(){
        const login = this.state.login;

        let panel = null;
        if (login) {
            panel = <Login/>;
        } else {
            panel = <Register/>;
        }
        return(
            <div className="authPanel">
            <Row>
                <Col sm={1} md={3} />
                <Col sm={10} md={6} >
                    <Nav bsStyle="tabs" activeKey={this.state.activeKey} justified onSelect={this.handleSelect.bind(this)}>
                            <NavItem className="login" eventKey="/login" title="login">
                                <h4>Login</h4>
                            </NavItem>
                            <NavItem className="register" eventKey="/register" title="register">
                                <h4>Register</h4>
                            </NavItem>
                    </Nav>
                    <Panel>
                        {panel}
                    </Panel>
                </Col>
                <Col sm={1} md={3} />
            </Row>
            </div>
        );
    }
}