import React, { useState } from 'react';
import axios from 'axios';
import RoomRulesModal from './RoomRulesModal';
import Modal from 'react-modal';

// Make sure to bind modal to your appElement (http://reactcommunity.org/react-modal/accessibility/)

Modal.setAppElement('#root');

const CreateRoom = () => {
    const [roomName, setRoomName] = useState('');
    const [roomDescription, setRoomDescription] = useState('');
    const [message, setMessage] = useState('');
    const [roomUrl, setRoomUrl] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/rooms/create', {
                roomName,
                roomDescription
            });
            setRoomUrl(response.data.roomUrl);
            setMessage('Room created successfully!');
            setIsModalOpen(true);  // Open the modal for room rules
        } catch (error) {
            setMessage('Error creating room.');
            console.error('There was an error!', error);
        }
    };

    const handleSaveRules = async (rules) => {
        try {
            await axios.post('/api/rooms/set-rules', {
                roomUrl,
                rules
            });
            setMessage('Rules set successfully!');
        } catch (error) {
            setMessage('Error setting rules.');
            console.error('There was an error!', error);
        }
    };

    return (
        <div>
            <h2>Create a Room</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Room Name:</label>
                    <input
                        type="text"
                        value={roomName}
                        onChange={(e) => setRoomName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Room Description:</label>
                    <input
                        type="text"
                        value={roomDescription}
                        onChange={(e) => setRoomDescription(e.target.value)}
                    />
                </div>
                <button type="submit">Create Room</button>
            </form>
            {message && <p>{message}</p>}
            {roomUrl && (
                <div>
                    <p>Room URL: <a href={roomUrl}>{roomUrl}</a></p>
                </div>
            )}
            <RoomRulesModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSave={handleSaveRules}
            />
        </div>
    );
};

export default CreateRoom;
