import React from 'react';
import {Nav,NavItem,Panel} from 'react-bootstrap';
import Login from './Login';
import Register from './Register';
import {Row,Col} from 'react-bootstrap';

export default class AuthPanel extends React.Component {
    constructor(props){
        super(props);
        var activeKey=this.props.location.pathname;
        var registrationComplete=false;
        if(activeKey=="/registration/complete")
        {
            activeKey="/login";
            registrationComplete=true;
        }
        this.state = {activeKey: activeKey,registrationComplete: registrationComplete};
    }

    getInitialState() {
        return {activeKey: this.props.location.pathname};
    }

    handleSelect(eventKey) {
        this.setState({activeKey: eventKey});

    }


    render(){
        let panel = null;

        if (this.state.activeKey=="/login")
            panel = <Login registrationComplete={this.state.registrationComplete}/>;
        else if(this.state.activeKey=="/register")
            panel = <Register/>;
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