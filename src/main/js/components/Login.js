import React from 'react';
import {Form,FormGroup,ControlLabel,FormControl,Button} from 'react-bootstrap';

export default class LoginForm extends React.Component {
    constructor(props){
        super(props);
    }

    login(){

    }

    render(){
        return(
            <Form>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>User name:</ControlLabel></h4>
                    <FormControl type="text" id="username" placeholder="Jarek512"/>
                </FormGroup>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Password:</ControlLabel></h4>
                    <FormControl type="password" id="password"/>
                </FormGroup>
                <Button bsStyle="primary" bsSize="large" onClick={this.login.bind(this)} block>
                    Login
                </Button>
            </Form>
        );
    }
}