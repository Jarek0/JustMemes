import React from 'react';
import ReactDOM from 'react-dom';
import { browserHistory } from 'react-router';
import {Form,FormGroup,ControlLabel,FormControl,Button,Checkbox,Overlay} from 'react-bootstrap';

export default class LoginForm extends React.Component {
    constructor(props){
        super(props);
        this.state = {errorMessage:"",id:""};
    }

    login(){
        const username=this.username.value;
        const password=this.password.value;
        const rememberMe=this.rememberMe.value;
        $.ajax({
            url: '/login',
            type: 'POST',
            success: (function(data) {
                browserHistory.push("");
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                var error=JSON.parse(xhr.responseText);
                var message=error.message.substring(error.message.indexOf(":")+1);
                console.log(message.substring(1,message.indexOf(":")));
                if(message.substring(1,message.indexOf(":"))==="notVerified")
                {
                    this.setState({errorMessage: "Your account is not verified. If you did not get verification e-mail click button below to resend it."});
                    this.setState({id:message.substr(message.indexOf(":")+1)});
                }
                else
                this.setState({errorMessage: message});
            }).bind(this),
            data:'username=' + username + '&password=' + password +'&remember-me='+rememberMe
        });
    }

    resend() {
        console.log(this.state.id);
        $.ajax({
            url: '/registration/resendToken',
            type: 'POST',
            contentType: "application/json",
            dataType: 'json',
            success: (function (data) {
                this.setState({id:data.message});
            }).bind(this),
            error: (function (xhr, ajaxOptions, thrownError) {
                console.log(xhr);
            }).bind(this),
            data: JSON.stringify({
                "message": this.state.id
            })
        });
    }
    render(){
        let resendButton = null;

        if (this.state.id==="")
            resendButton = null;
        else
            resendButton = <Button bsStyle="primary" ref="registrationButton" onClick={this.resend.bind(this)} block>
                Resend e-mail
            </Button>;

        return(
            <Form>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>User name:</ControlLabel></h4>
                    <FormControl type="text" id="username" inputRef={(ref) => {this.username = ref}} placeholder="Jarek512"/>
                </FormGroup>
                <FormGroup bsSize="large">
                    <h4><ControlLabel>Password:</ControlLabel></h4>
                    <FormControl type="password" id="password" inputRef={(ref) => {this.password = ref}}/>
                </FormGroup>
                <FormGroup bsSize="large">
                <Checkbox inputRef={(ref) => {this.rememberMe = ref}}>
                    <h4>Remember me</h4>
                </Checkbox>
                </FormGroup>
                <Overlay
                    show={this.state.errorMessage!=""}
                    placement="top"
                    container={this}
                    target={() => ReactDOM.findDOMNode(this.refs.loginButton)}
                >
                    <div style={{
                        border: '1px solid #747474',
                        borderRadius: 3,
                        margin: 3,
                        padding: 5
                    }}>
                        <h5><ControlLabel>
                            {this.state.errorMessage}
                            </ControlLabel></h5>
                        {resendButton}
                    </div>
                </Overlay>
                <Overlay
                    show={this.props.registrationComplete && this.state.errorMessage==""}
                    placement="top"
                    container={this}
                    target={() => ReactDOM.findDOMNode(this.refs.loginButton)}
                >
                    <div style={{
                        border: '1px solid #747474',
                        borderRadius: 3,
                        margin: 3,
                        padding: 5
                    }}>
                        <h5><ControlLabel>
                            Congratulation! You successfully register! Now you can login to your account!
                        </ControlLabel></h5>
                    </div>
                </Overlay>
                <Button bsStyle="primary" ref={(ref) => { this.loginButton = ref; }} bsSize="large" onClick={this.login.bind(this)} block>
                    Login
                </Button>

            </Form>
        );
    }
}