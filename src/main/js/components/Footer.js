import React from 'react';
import {Grid,Row,Col} from 'react-bootstrap';

export default class Footer extends React.Component{

    render(){
        return (
            <Grid className="down-block">
                <Row >
                    <Col md={2} lg={2}  />
                    <Col md={4} lg={4} className="first">
                    <p>
                        JustMemes is simple web application created in Spring Boot (backend) and React (frontend)
                        frameworks. It is simple entertainment web page where logged in users can share memes. New memes go to
                        the "waiting room" and if admins of system accept they they go to main page where every users
                        can see they. So, have fun with using our page! :)
                    </p>
                    <p>Created by: Jarosław Bielec & Katarzyna Buszewicz</p>
                    </Col>
                    <Col md={4} lg={4} className="second">
                    <a href="#">Contact</a>
                    <a href="#">FAQ</a>
                    <a href="#">Regulations</a>
                    </Col >
                    <Col md={2} lg={2}  />
                </Row>
                </Grid>
        );
    }
};
