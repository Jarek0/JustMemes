import React from 'react';
import {Form,FormGroup,ControlLabel,FormControl,Button} from 'react-bootstrap';

export default class LoginForm extends React.Component {
    constructor(props){
        super(props);
    }

    register(){
        var registerUser={
            username:this.refs.username,
            password:this.refs.password,
            passwordConfirm:this.refs.passwordConfirm,
            email:this.refs.email
        };
        console.log(registerUser);
        $.ajax({
            url: '/registration',
            type: 'POST',
            dataType: 'json',
            success: (function(data) {
                console.log(data);
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                console.log(xhr);
            }).bind(this),
            data: registerUser
        });
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
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Confirm password:</ControlLabel></h4>
                    <FormControl type="password" id="passwordConfirm"/>
                </FormGroup>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Email address:</ControlLabel></h4>
                    <FormControl type="email" id="email" placeholder="jarek512@gmail.com"/>
                </FormGroup>
                <Button bsStyle="primary" bsSize="large" onClick={this.register.bind(this)} block>
                    Register
                </Button>
            </Form>
        );
    }
}